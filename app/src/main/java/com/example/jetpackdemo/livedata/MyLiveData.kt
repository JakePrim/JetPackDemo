package com.example.jetpackdemo.livedata

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.arch.core.internal.SafeIterableMap
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

/**
 * 手写LiveData实现
 */
class MyLiveData<T> {
    private val START_VERSION = -1

    private var mData:T?= null

    private var mVersion:Int = START_VERSION

    private var mPaddingData:T? = null

    private val H = Handler(Looper.getMainLooper())

    private var mObserves = ConcurrentHashMap<Observe<T>, ObserveWrapper>()

    private val currentVersion = AtomicInteger(START_VERSION)

    /**
     * 发布数据 可以在任意线程
     */
    fun postValue(t:T){
        H.post {
            setValue(t)
        }
    }

    /**
     * 发布数据 只能在主线程
     */
    @MainThread
    fun setValue(t:T){
        //判断是否在主线程
        if (Thread.currentThread() != Looper.getMainLooper().thread) {
            //如果不在主线程 抛出异常
            throw IllegalAccessException("setValue 必须要在主线程中调用")
        }
        currentVersion.getAndIncrement()
        mData = t
        mVersion++
        dispatchValue(null)
    }

    /**
     * 订阅(粘性)消息
     */
    @MainThread
    fun addObserve(owner: LifecycleOwner,observe: Observe<T>){
        val lifecycleObserve = LifecycleObserveWrapper(owner, observe)
        mObserves[observe] = lifecycleObserve
        owner.lifecycle.addObserver(lifecycleObserve)
    }

    /**
     * 订阅非粘性消息
     */
    @MainThread
    fun addNoStickinessObserve(owner: LifecycleOwner,observe: Observe<T>){
        this.addObserve(owner,NoStickinessObserve(observe,currentVersion.get()))
    }

    /**
     * 分发数据
     */
    private fun dispatchValue(wrapper: ObserveWrapper?){
        if (wrapper == null){
            mObserves.entries.forEach {
                notifyObserve(it.value)
            }
        }else{
            notifyObserve(wrapper)
        }
    }


    /**
     * 通知订阅
     */
    private fun notifyObserve(observe:ObserveWrapper){
        if (!observe.mActive){
            return
        }
        if (!observe.shouldActive()){
            observe.activeStateChange(false)
            return
        }
        if (observe.mLastVersion >= mVersion){
            return
        }
        observe.mLastVersion = mVersion
        observe.observe.onChange(mData)
    }

    /**
     * 监听生命周期
     */
    inner class LifecycleObserveWrapper(private val lifecycleOwner: LifecycleOwner,observe: Observe<T>):ObserveWrapper(observe = observe),LifecycleEventObserver{
        /**
         * 判断activity是否处于活跃状态
         */
        override fun shouldActive():Boolean{
            return lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (source.lifecycle.currentState == Lifecycle.State.DESTROYED){
                return
            }
            activeStateChange(shouldActive())
        }
    }

    abstract inner class ObserveWrapper(val observe: Observe<T>){
        var mLastVersion = START_VERSION

        var mActive:Boolean = false

        abstract fun shouldActive():Boolean

        fun activeStateChange(mActive: Boolean){
            if (this.mActive == mActive){
                return
            }
            this.mActive = mActive
            if (mActive){
                dispatchValue(this)
            }
        }
    }

    /**
     * 监听回调
     */
    interface Observe<T>{
        fun onChange(value:T?)
    }

    /**
     * 没有粘性事件的监听回调
     */
    inner class NoStickinessObserve<T>(private val observe: Observe<T>, private val version:Int = START_VERSION):Observe<T>{
        override fun onChange(value: T?) {
            if (currentVersion.get() > version){
                observe.onChange(value)
            }
        }
    }
}



