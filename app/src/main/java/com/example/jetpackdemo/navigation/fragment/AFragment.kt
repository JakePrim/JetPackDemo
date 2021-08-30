package com.example.jetpackdemo.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.FragmentTestBinding

class AFragment : Fragment() {
    lateinit var dataBinding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate<FragmentTestBinding>(
            inflater,
            R.layout.fragment_test,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnAToB.setOnClickListener {
            val bundle = Bundle().apply {
                putString("key", "value")
            }
            //跳转并传递参数navigate:传递action的id
            Navigation.findNavController(requireView())
                .navigate(R.id.action_afragment_to_bfragment, bundle)
        }
        dataBinding.btnAToE.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_afragment_to_efragment)
        }
    }
}