package com.example.jetpackdemo.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.HashMap

/**
 * LiveDataBus 用于定义全局的LiveData 实现跨组件通信
 */
class LiveDataBus {
    private var warehouse: HashMap<String, UnPeekLiveData<Any>> = HashMap()

    companion object {
        private var liveDataBus: LiveDataBus? = null

        fun getInstance(): LiveDataBus? {
            if (liveDataBus == null) {
                synchronized(LiveDataBus::class.java) {
                    if (liveDataBus == null) {
                        liveDataBus = LiveDataBus()
                    }
                }
            }
            return liveDataBus
        }
    }

    fun with(key: String): UnPeekLiveData<Any>? {
        return if (!warehouse.containsKey(key)) {
            val liveData = UnPeekLiveData<Any>()
            warehouse[key] = liveData
            liveData
        } else {
            warehouse.get(key = key)
        }
    }

}

/**
 * 粘性事件的问题修复
 * 重写observer onChange判断版本号-推荐的版本
 */
class UnPeekLiveData<T>:MutableLiveData<T>(){
    private val currentVersion = AtomicInteger(START_VERSION)
    companion object{
        private const val START_VERSION = -1
    }

    /**
     * 非粘性事件
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, ObserveWrapper(observer,currentVersion.get()))
    }

    /**
     * 粘性事件
     */
    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>){
        super.observe(owner, ObserveWrapper(observer))
    }

    override fun setValue(value: T) {
        currentVersion.getAndIncrement()
        super.setValue(value)
    }

    inner class ObserveWrapper(private val mObserver: Observer<in T>, private val mVersion:Int = START_VERSION):Observer<T>{

        override fun onChanged(t: T) {
            if (currentVersion.get() > mVersion && t != null){
                mObserver.onChanged(t)
            }
        }

        override fun equals(o: Any?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || javaClass != o::class) {
                return false
            }
            val that = o as UnPeekLiveData<*>.ObserveWrapper
            return mObserver == that.mObserver
        }

        override fun hashCode(): Int {
            return Objects.hash(mObserver)
        }

    }
}