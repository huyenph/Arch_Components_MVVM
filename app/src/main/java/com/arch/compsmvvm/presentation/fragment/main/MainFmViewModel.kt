package com.arch.compsmvvm.presentation.fragment.main

import android.arch.lifecycle.MutableLiveData
import com.arch.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.arch.compsmvvm.data.remote.response.question.QuestionResponse
import com.arch.compsmvvm.data.repository.Repository
import com.arch.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFmViewModel(private val repository: Repository) : BaseViewModel(repository) {
    var questionLive: MutableLiveData<MutableList<QuestionItemResponse>> = MutableLiveData()

    fun getQuestion(site: String, page: Int, loading: Boolean) {
        if (loading) showLoading()
        val disposables = repository.getQuestion(site, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<QuestionResponse>() {}.type
                    val question = Gson().fromJson(it, type) as QuestionResponse
                    questionLive.value = question.items
                }
                dismissLoading()
                hideMessage()
            }, {
                dismissLoading()
                showMessage(it.message!!)
            })
        compositeDisposable.add(disposables)
    }
}