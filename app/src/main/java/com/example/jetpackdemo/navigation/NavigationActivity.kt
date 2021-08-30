package com.example.jetpackdemo.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.jetpackdemo.R
import com.example.jetpackdemo.navigation.fragment.EFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Navigation 与 Fragment，在单Activity以Fragment作为页面的开发模式
 * Navigation: 管理Fragment的回退栈
 * 导航图
 * NavHost : 空白容器，管理所有的fragment
 * NavController: 辅助NavHost中目标内容的交换
 */
class NavigationActivity : AppCompatActivity(), EFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        //NavController 控制 NavHostFragment 空白容器 管理Fragments
        val navController = Navigation.findNavController(this, R.id.nav_host)

        //NavigationUI 对AppBar BottomNav与NavController进行绑定 menu绑定,menu的id与Navigation的graph中定义的id一定要一致否则无法跳转
        NavigationUI.setupWithNavController(bottom_nav, navController)

        // 动态配置bottom_nav 的跳转fragment，不需要menu 的id和graph的id一致
        // 但是每次都会存入到 回退栈中
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.afragment -> {
                    navController.navigate(R.id.action_to_afragment)
                }
                R.id.bfragment -> {
                    navController.navigate(R.id.action_to_bfragment)
                }
                R.id.cfragment -> {
                    navController.navigate(R.id.action_to_cfragment)
                }
                R.id.dfragment -> {
                    navController.navigate(R.id.action_to_dfragment)
                }
            }
            //true 拦截跳转
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onFragmentInteraction(name: String) {
        Toast.makeText(this, "Channel:$name", Toast.LENGTH_SHORT).show()
    }

}