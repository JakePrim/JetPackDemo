package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    val selected = MutableLiveData<String>()

    fun select(value:String){
        selected.value = value
    }
}