package com.example.jetpackdemo.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.App
import com.example.jetpackdemo.R

class TestActivity : AppCompatActivity() {
    val tv:TextView by lazy { findViewById(R.id.tv) }

    val model:RotatedViewModel by lazy {
        ViewModelProvider(this.applicationContext as App).get(RotatedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        model.observerNumber(this){
            tv.text = it.toString()
        }
    }

    fun onChange(view: View) {
        model.setNumber(100)
    }
}