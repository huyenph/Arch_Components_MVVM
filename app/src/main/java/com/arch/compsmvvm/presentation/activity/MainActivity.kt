package com.arch.compsmvvm.presentation.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import com.arch.compsmvvm.R
import com.arch.compsmvvm.data.remote.response.site.SiteItemResponse
import com.arch.compsmvvm.databinding.ActivityMainBinding
import com.arch.compsmvvm.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.arch.compsmvvm.presentation.adapter.MenuAdapter
import com.arch.compsmvvm.presentation.base.BaseAdapter
import com.arch.compsmvvm.presentation.fragment.main.MainFragment
import com.arch.compsmvvm.presentation.fragment.question.QuestionFragment
import com.arch.compsmvvm.presentation.fragment.site.SiteFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), BaseAdapter.AdapterListener {
    private val vm: MainViewModel by viewModel()
    private val menuLm = GridLayoutManager(this, 1)
    private var menuAdapter: MenuAdapter? = null
    private var menus: MutableList<SiteItemResponse> = ArrayList()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = vm
        configNavigation(0, elevation = true, scrim = true)
        init()
    }

    private fun init() {
        addFragment(MainFragment(), addToBackStack = true, animation = false)
        menuAdapter = MenuAdapter(actMain_rvMenu, 0, R.layout.view_footer_menu, null, this)
        actMain_rvMenu.run {
            layoutManager = menuLm
            setHasFixedSize(true)
            adapter = menuAdapter
        }
        vm.loadMenu(page)
        vm.menuLive!!.observe(this, Observer {
            if (it != null) {
                if (menus.size > 50) {
                    menuAdapter!!.isEndList = true
                    menuAdapter!!.notifyDataSetChanged()
                } else {
                    menus.addAll(it)
                    menuAdapter!!.set(menus)
                    menuAdapter!!.isLoading = true
                }
            }
        })
    }

    override fun onItemClick(`object`: Any, position: Int) {
        actMain_dl.closeDrawer(Gravity.START)
        if (`object` is SiteItemResponse) {
            if (`object`.apiSiteParameter != "stackoverflow") {
                replaceFragment(
                    QuestionFragment.newInstance(`object`.name!!, `object`.apiSiteParameter!!),
                    addToBackStack = true,
                    animation = false
                )
            }
        } else if (`object` is String) {
            addFragment(SiteFragment(), addToBackStack = true, animation = true)
        }
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        vm.loadMenu(++page)
    }
}
