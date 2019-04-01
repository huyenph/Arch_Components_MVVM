package com.arch.compsmvvm.presentation.fragment.site

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arch.compsmvvm.R
import com.arch.compsmvvm.data.remote.response.site.SiteItemResponse
import com.arch.compsmvvm.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.arch.compsmvvm.databinding.FragmentSiteBinding
import com.arch.compsmvvm.presentation.adapter.SiteAdapter
import com.arch.compsmvvm.presentation.base.BaseAdapter
import com.arch.compsmvvm.presentation.fragment.question.QuestionFragment
import kotlinx.android.synthetic.main.fragment_site.view.*

class SiteFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private val vm by viewModel<SiteViewModel>()
    private lateinit var mView: View
    private lateinit var siteLm: GridLayoutManager
    private var siteAdapter: SiteAdapter? = null
    private val sites: MutableList<SiteItemResponse> = ArrayList()
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSiteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_site, container, false)
        binding.viewModel = vm
        mView = binding.root
        configToolbar(mView, getString(R.string.all_sites), null)
        init()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadAllSite(page, true)
        vm.siteLive!!.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    siteAdapter!!.isEndList = true
                    siteAdapter!!.notifyDataSetChanged()
                } else {
                    sites.addAll(it)
                    siteAdapter!!.set(sites)
                    siteAdapter!!.isLoading = true
                }
            }
        })
    }

    private fun init() {
        siteLm = GridLayoutManager(context, 2)
        siteLm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(p0: Int): Int {
                return when (siteAdapter!!.getItemViewType(p0)) {
                    BaseAdapter.TYPE_LOADING -> 2
                    else -> 1
                }
            }
        }
        if (siteAdapter == null) {
            siteAdapter = SiteAdapter(mView.fmSite_rvContent, siteLm, this)
        }
        mView.fmSite_rvContent.run {
            layoutManager = siteLm
            adapter = siteAdapter
            setHasFixedSize(true)
        }
        mView.fmSite_srl.setOnRefreshListener {
            page = 1
            sites.clear()
            siteAdapter!!.set(sites)
            vm.loadAllSite(page, true)
            mView.fmSite_srl.isRefreshing = false
        }
    }

    override fun onItemClick(`object`: Any, position: Int) {
        if (`object` is SiteItemResponse) {
            replaceFragment(
                QuestionFragment.newInstance(`object`.name!!, `object`.apiSiteParameter!!),
                addToBackStack = true,
                animation = true
            )
        }
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        vm.loadAllSite(++page, false)
    }
}