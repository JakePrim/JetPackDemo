package com.example.jetpackdemo.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R

class ViewModelActivity : AppCompatActivity() {

    val rotatedViewModel: RotatedViewModel =
        ViewModelProvider(this).get(RotatedViewModel::class.java)

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        //viewmodel 可以在fragment之间互相通信
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction
            .add(R.id.fl_master, MasterFragment.newInstance())
            .add(R.id.fl_detail, DetailFragment.newInstance())
            .commitAllowingStateLoss()
        //屏幕旋转会导致tv重建 需要重新赋值
        tv = findViewById(R.id.tv)
        //查看屏幕旋转 重建activity后viewmodel是否可以做到数据持久化操作
        tv.text = rotatedViewModel.number.toString()
    }

    fun onChange(view: View) {
        rotatedViewModel.number++
        tv.text = rotatedViewModel.number.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        //屏幕旋转 activity是否会销毁重建  查看viewmodel 是否会重新实例化
        Log.e("TAG->>>>", "onDestroy: ${hashCode()}  viewmodel:${rotatedViewModel.hashCode()}")
    }
}
