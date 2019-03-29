package com.arch.compsmvvm.presentation.fragment.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arch.compsmvvm.R
import com.arch.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.arch.compsmvvm.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.arch.compsmvvm.databinding.FragmentMainBinding
import com.arch.compsmvvm.presentation.adapter.QuestionAdapter
import com.arch.compsmvvm.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private val vm by viewModel<MainFmViewModel>()
    private lateinit var mView: View
    private lateinit var questionLm: GridLayoutManager
    private var questionAdapter: QuestionAdapter? = null
    private val questions: MutableList<QuestionItemResponse> = ArrayList()
    private var page = 1

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
        vm.questionLive!!.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    questionAdapter!!.isEndList = true
                    questionAdapter!!.notifyDataSetChanged()
                } else {
                    questions.addAll(it)
                    questionAdapter!!.set(questions)
                    questionAdapter!!.isLoading = true
                }
            }
        })
    }

    private fun init() {
        questionLm = GridLayoutManager(context, 1)
        if (questionAdapter == null) {
            questionAdapter = QuestionAdapter(mView.fmMain_rvContent, questionLm, this)
        }
        mView.fmMain_rvContent.run {
            layoutManager = questionLm
            adapter = questionAdapter
            setHasFixedSize(true)
        }
        mView.fmMain_srl.setOnRefreshListener {
            page = 1
            questions.clear()
            questionAdapter!!.set(questions)
            vm.getQuestion("stackoverflow", page, true)
            mView.fmMain_srl.isRefreshing = false
        }
    }

    override fun onItemClick(`object`: Any, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        vm.getQuestion("stackoverflow", ++page, false)
    }
}