package com.example.jetpackdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemo.databinding.DataBindingActivity
import com.example.jetpackdemo.lifecycle.LifecycleActivity
import com.example.jetpackdemo.livedata.LiveDataActivity
import com.example.jetpackdemo.mvvm.MvvmActivity
import com.example.jetpackdemo.navigation.NavigationActivity
import com.example.jetpackdemo.room.RoomActivity
import com.example.jetpackdemo.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLiveData(view: View) {
        startActivity(Intent(this, LiveDataActivity::class.java))
    }

    fun onLifecycle(view: View) {
        startActivity(Intent(this, LifecycleActivity::class.java))
    }

    fun onViewModel(view: View) {
        startActivity(Intent(this, ViewModelActivity::class.java))
    }

    fun onDataBinding(view: View) {
        startActivity(Intent(this, DataBindingActivity::class.java))
    }

    fun onNavigation(view: View) {
        startActivity(Intent(this, NavigationActivity::class.java))
    }

    fun onMVVM(view: View) {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun onRoom(view: View) {
        startActivity(Intent(this, RoomActivity::class.java))
    }
    fun onWorkManager(view: View) {}
    fun onPaging(view: View) {}
}