package com.example.jetpackdemo

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App:Application(),ViewModelStoreOwner {

    /**
     * 存在Application作用域的ViewModel
     */
    private lateinit var mAppViewModelStore:ViewModelStore

    companion object{
        lateinit var getApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        getApplicationContext = applicationContext
        mAppViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
}