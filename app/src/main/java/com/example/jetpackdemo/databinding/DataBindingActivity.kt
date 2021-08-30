package com.example.jetpackdemo.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.model.JUser
import com.example.jetpackdemo.databinding.model.User

class DataBindingActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityDataBindingBinding

    val viewModel:MyViewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        //第一种单向绑定方式
        init_1()
        //第二种单向绑定的方式
        init_2()
        //第三种单向绑定的方式
        init_3()
        //第四种单向绑定的方式
        init_4()

        //双向绑定 通过@={} view 改变 model也会改变。
    }

    private fun init_4(){
        //LiveData 只能是String的时候才可以
        viewModel.number.value = "11"
        viewModel.user.value = User()

        //监听数据变化 根据LiveData修改数据 需要监听LiveData 重新设置viewModel
        viewModel.number.observe(this){
            dataBinding.viewModel = viewModel
        }

        dataBinding.btn.setOnClickListener {
            viewModel.number.value = "20"
            viewModel.user.value!!.name.set("zhangsan")
            viewModel.name.set("lisi")
        }
    }

    private fun init_3() {
        val name = ObservableField<String>("zhangsan")
        val age = ObservableField<String>("11")
        dataBinding.name = name
        dataBinding.age = age
        dataBinding.btn3.setOnClickListener {
            name.set("aa")
            age.set("29")
        }
    }

    private fun init_2() {
        val user = User()
        user.name.set("11")
        user.age.set("22")
        dataBinding.user = user
        dataBinding.btn2.setOnClickListener {
            user.name.set("aa")
            user.age.set("29")
        }
    }

    private fun init_1() {
        val user = JUser("ss", "28")
        //注意常见错误点1：User中 存在Int但是给TextView 赋值为Int是会出现错误的因为TextView.setText不能设置Int类型 只能是String类型
        dataBinding.juser = user //更新布局

        //代替了findViewById
        dataBinding.btn1.setOnClickListener {
            user.name = "aa"
            user.age = "29"
        }
    }
}