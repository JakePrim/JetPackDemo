package com.example.jetpackdemo.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.FragmentBBinding

class BFragment : Fragment() {
    lateinit var dataBinding: FragmentBBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //获取参数 bundle传递的参数并赋值
        val value = arguments?.getString("key")

        //获取graph设置的传递参数
        val value1 = arguments?.getString("name")

        val name = ObservableField<String>(value)
        val name2 = ObservableField<String>(value1)
        dataBinding.name = name

        dataBinding.name2 = name2
    }
}