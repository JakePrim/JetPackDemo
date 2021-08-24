package com.example.jetpackdemo.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.jetpackdemo.R

class LifecycleActivity : LActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        //感知生命周期
        lifecycle.addObserver(MyLifecycleObserver())
    }
}

/**
 * 监听生命周期
 */
class MyLifecycleObserver:LifecycleEventObserver{
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.e("MyLifecycleObserver", "onStateChanged: ${source.lifecycle.currentState} event:${event}" )
    }
}