package com.utildev.compsmvvm.presentation.fragment.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.compsmvvm.R
import com.utildev.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.utildev.compsmvvm.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.compsmvvm.databinding.FragmentMainBinding
import com.utildev.compsmvvm.presentation.adapter.QuestionAdapter
import com.utildev.compsmvvm.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private val vm by viewModel<MainFmViewModel>()
    private lateinit var mView: View
    private var questionLm: GridLayoutManager? = null
    private var questionAdapter: QuestionAdapter? = null
    private var questions: ArrayList<QuestionItemResponse>? = null
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.questionLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    questionAdapter!!.isEndList = true
                    questionAdapter!!.notifyDataSetChanged()
                } else {
                    questions!!.addAll(it)
                    questionAdapter!!.set(questions!!)
                    questionAdapter!!.isLoading = true
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = vm
        mView = binding.root
        configToolbarMain(mView, "Stack Overflow")
        init()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getQuestion("stackoverflow", page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getQuestion("stackoverflow", ++page, false)
    }

    private fun init() {
        questionLm = GridLayoutManager(context, 1)
        questionAdapter = QuestionAdapter(mView.fmMain_rvContent, questionLm, this)
        questions = ArrayList()
        page = 1
        mView.fmMain_rvContent.run {
            layoutManager = questionLm
            adapter = questionAdapter
            setHasFixedSize(true)
        }
        mView.fmMain_srl.setOnRefreshListener {
            page = 1
            questions!!.clear()
            questionAdapter!!.set(questions!!)
            vm.getQuestion("stackoverflow", page, true)
            mView.fmMain_srl.isRefreshing = false
        }
    }
}