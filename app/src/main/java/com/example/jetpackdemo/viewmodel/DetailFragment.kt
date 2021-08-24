package com.example.jetpackdemo.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R

class DetailFragment:Fragment() {

    companion object{
        fun newInstance() = DetailFragment()
    }

    private val model:SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv_detail = view.findViewById<TextView>(R.id.tv_detail)
//        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.selected.observe(viewLifecycleOwner){
            tv_detail.text = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("DetailFragment->>>>", "onDestroy: ${hashCode()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("DetailFragment->>>>", "onDestroyView: ${hashCode()}")
    }
}