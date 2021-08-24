package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

/**
 * 旋转屏幕 查看viewmodel是否可以做到持久化
 */
class RotatedViewModel:ViewModel() {
    //ViewModel与LiveData结合 LiveData尽量不要让外部获取到 防止发生不可预期的问题
    private val numberData = MutableLiveData<Int>()

    public fun observerNumber(owner: LifecycleOwner,observer: Observer<Int>){
        /**
         * Observe 如之前讲述的LiveData 可以拿到最新的数据 然后调用回调
         */
         numberData.observe(owner,observer)
    }

    public fun getNumber():Int{
        return numberData.value!!
    }

    public fun setNumber(value:Int){
        numberData.value = value
    }
}