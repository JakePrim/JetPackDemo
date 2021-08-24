package com.example.jetpackdemo.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.App
import com.example.jetpackdemo.R

class ViewModelActivity : AppCompatActivity() {

    //获取viewmodel
    lateinit var rotatedViewModel: RotatedViewModel

    lateinit var tv: TextView

    //类似savedInstanceState Activity重建会传入之前的
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        //get ViewModelStore获取ViewModel对象如果没有则创建  ViewModel的作用域设置为Application 可以在Activity之间共享状态
        rotatedViewModel  = ViewModelProvider(this.applicationContext as App).get(RotatedViewModel::class.java)
        //viewmodel 可以在fragment之间互相通信 需要将ViewModel的作用域设置为Activity
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction
            .add(R.id.fl_master, MasterFragment.newInstance())
            .add(R.id.fl_detail, DetailFragment.newInstance())
            .commitAllowingStateLoss()
        //屏幕旋转会导致tv重建 需要重新赋值
        tv = findViewById(R.id.tv)
        //查看屏幕旋转 重建activity后viewmodel是否可以做到数据持久化操作
        rotatedViewModel.observerNumber(this){
            Log.e("TAG", "onCreate: ${lifecycle.currentState}" )//TAG: onCreate: STARTED RESUMED
            tv.text = it.toString()
        }

    }

    fun onChange(view: View) {
        rotatedViewModel.setNumber(11)
    }

    override fun onDestroy() {
        super.onDestroy()
        //屏幕旋转 activity是否会销毁重建  查看viewmodel 是否会重新实例化
        Log.e("ViewModelActivity->>>>", "onDestroy: ${hashCode()}  viewmodel:${rotatedViewModel.hashCode()}")
    }

    fun onActChange(view: View) {
        rotatedViewModel.setNumber(10)
        startActivity(Intent(this,TestActivity::class.java))
    }
}
