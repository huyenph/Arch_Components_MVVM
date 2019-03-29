package com.arch.compsmvvm.presentation.fragment.question

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arch.compsmvvm.R
import com.arch.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.arch.compsmvvm.databinding.FragmentQuestionBinding
import com.arch.compsmvvm.presentation.adapter.QuestionAdapter
import com.arch.compsmvvm.presentation.base.BaseAdapter
import com.arch.compsmvvm.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_question.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment: BaseFragment(), BaseAdapter.AdapterListener {
    private val vm by viewModel<QuestionViewModel>()
    private lateinit var mView: View
    private lateinit var questionLm: GridLayoutManager
    private var questionAdapter: QuestionAdapter? = null
    private val questions: MutableList<QuestionItemResponse> = ArrayList()
    private var siteParam = ""
    private var page = 1

    companion object {
        fun newInstance(siteName: String, siteParam: String) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putString("name", siteName)
                putString("param", siteParam)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.viewModel = vm
        mView = binding.root
        init()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fmQuestion_rvContent.run {
            layoutManager = questionLm

            setHasFixedSize(true)
            adapter = questionAdapter
        }
    }

    private fun init() {
        if (arguments != null) {
            configToolbar(mView, arguments!!.getString("name", ""), null)
            siteParam = arguments!!.getString("param", "")
        }
        questionLm = GridLayoutManager(context, 1)
        if (questionAdapter == null) {
            questionAdapter = QuestionAdapter(mView.fmQuestion_rvContent, questionLm, this)
        }

        mView.fmQuestion_srl.setOnRefreshListener {
            page = 1
            questions.clear()
            questionAdapter!!.set(questions)
            vm.getQuestion(siteParam, page, true)
            mView.fmQuestion_srl.isRefreshing = false
        }

        vm.getQuestion(siteParam, page, true)
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

    override fun onItemClick(`object`: Any, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        vm.getQuestion(siteParam, ++page, false)
    }
}