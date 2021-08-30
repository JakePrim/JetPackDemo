package com.example.jetpackdemo.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.databinding.model.User

class MyViewModel:ViewModel() {
    val name = ObservableField<String>()

    public val number:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    public val user:MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
}