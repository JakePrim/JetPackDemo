package com.example.jetpackdemo.livedata

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 感知生命周期
 */
class MyLifeCycle : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.e("TAG", "onStateChanged: " + source.lifecycle.currentState+" event:"+event)
    }
}