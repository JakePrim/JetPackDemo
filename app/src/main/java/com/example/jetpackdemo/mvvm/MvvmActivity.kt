package com.example.jetpackdemo.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackdemo.R

/**
 * MVVM 架构设计
 * App 模块 只需要处理业务逻辑模块
 */
class MvvmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
    }
}