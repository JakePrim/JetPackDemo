package com.example.jetpackdemo.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpackdemo.R
import kotlin.concurrent.thread
import kotlin.math.log

/**
 * LiveData 框架分析
 */
class LiveDataActivity : AppCompatActivity() {
    lateinit var liveData: MyLiveData<String>

    val ld = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        //感知Activity的生命周期 注册生命周期的观察者
        lifecycle.addObserver(MyLifeCycle())

        //<T> 决定livedata 持有的数据类型
        liveData = MyLiveData<String>()

        //注册观察者
        //LifecycleOwner AppCompatActivity进行了实现
        liveData.addObserve(this,object :MyLiveData.Observe<String>{
            override fun onChange(value: String?) {
                Log.e("TAG", "onChange: 我是自定义$value")
            }
        })

        //设置持有的数据
        //postValue 可以在任意的线程下执行
        liveData.postValue("1")
        thread {
            liveData.postValue("3")
        }
        //setValue 只能在主线程执行
        liveData.setValue("2")
    }

    fun setValue(view: View) {
        liveData.postValue("11111")
        ld.postValue("22222")
    }


    fun setViscous(view: View) {
        //再次注册 生命周期的观察者  会打印当前的生命周期状态
        lifecycle.addObserver(MyLifeCycle())

        ld.observe(this){
            Log.e("TAG", "setViscous: ${it}")
        }

        ld.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                Log.e("TAG", "onChanged: ${t}" )
            }
        })

        //粘性事件 liveData同理 注册生命周期的观察者
        liveData.addNoStickinessObserve(this,object :MyLiveData.Observe<String>{
            override fun onChange(value: String?) {
                Log.e("TAG", "onChange: 粘性事件：$value")
            }
        })
    }

    fun setLifecyle(view: View) {
        //延时执行 app进入后台 10S在进入前台 查看数据
        Handler(Looper.getMainLooper()).postDelayed({
            liveData.postValue("lifecyle")
        }, 5000)
    }

    fun onGoTo(view: View) {
        val liveData = LiveDataBus.getInstance()?.with("test")
        liveData?.observeSticky(this){
            Log.e("TAG", "onGoTo: $it" )
        }
        //跳转到其他组件
        startActivity(Intent(this, LiveData2Activity::class.java))
    }
}