package com.arch.compsmvvm.presentation.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.arch.compsmvvm.R
import com.arch.compsmvvm.data.remote.response.site.SiteItemResponse
import com.arch.compsmvvm.databinding.ActivityMainBinding
import com.arch.compsmvvm.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.arch.compsmvvm.presentation.adapter.MenuAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val vm: MainViewModel by viewModel()
    private val menuLm = GridLayoutManager(this, 1)
    private var menuAdapter: MenuAdapter? = null
    private var menus: MutableList<SiteItemResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = vm
        configNavigation(0, elevation = true, scrim = true)
        init()
    }

    private fun init() {
        menuAdapter = MenuAdapter(actMain_rvMenu, 0, 0, null, null)
        actMain_rvMenu.run {
            layoutManager = menuLm
            setHasFixedSize(true)
            adapter = menuAdapter
        }

        vm.loadMenu()
        vm.menuLive!!.observe(this, Observer {
            if (it != null) {
                menus.addAll(it)
                menuAdapter!!.set(menus)
            }
        })
    }
}
