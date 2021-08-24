package com.example.jetpackdemo.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.jetpackdemo.R

class LiveData2Activity : AppCompatActivity() {
     private var liveData:UnPeekLiveData<Any>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data2)
        val tv = findViewById<TextView>(R.id.tv)
        liveData = LiveDataBus.getInstance()?.with("test")

    }

    fun onValue(view: View) {
        liveData?.postValue("test") //将数据发送给 LiveData3Activity
    }
}