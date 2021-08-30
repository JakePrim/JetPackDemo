package com.example.jetpackdemo.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

/**
 * 单向绑定的第二种方法 - 推荐
 */
data class User(var name: ObservableField<String> = ObservableField("name"), var age: ObservableField<String> = ObservableField("18"))