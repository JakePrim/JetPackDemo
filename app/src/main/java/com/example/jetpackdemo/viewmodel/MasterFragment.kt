package com.example.jetpackdemo.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R

class MasterFragment:Fragment() {

    companion object{
        fun newInstance() = MasterFragment()
    }

    private val model:SharedViewModel by activityViewModels() //ktx 扩展方法

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_master,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val btn_master = view.findViewById<Button>(R.id.btn_master)
        btn_master.setOnClickListener {
            model.select("Master")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MasterFragment->>>>", "onDestroy: ${hashCode()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("MasterFragment->>>>", "onDestroyView: ${hashCode()}")
    }
}