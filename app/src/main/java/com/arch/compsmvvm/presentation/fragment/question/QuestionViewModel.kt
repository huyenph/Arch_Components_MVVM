package com.arch.compsmvvm.presentation.fragment.question

import android.util.Log
import com.arch.compsmvvm.common.helper.SingleLiveData
import com.arch.compsmvvm.data.remote.response.error.ErrorResponse
import com.arch.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.arch.compsmvvm.data.remote.response.question.QuestionResponse
import com.arch.compsmvvm.data.repository.Repository
import com.arch.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class QuestionViewModel(private val repository: Repository): BaseViewModel(repository) {
    var questionLive: SingleLiveData<MutableList<QuestionItemResponse>>? = null

    fun getQuestion(site: String, page: Int, loading: Boolean) {
        if (loading) showLoading()
        if (questionLive == null) questionLive = SingleLiveData()
        val disposables = repository.getQuestion(site, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<QuestionResponse>() {}.type
                    val question = Gson().fromJson(it, type) as QuestionResponse
                    questionLive!!.value = question.items
                }
                dismissLoading()
                hideMessage()
            }, {
//                Log.d("aaa", it.printStackTrace().toString())
//                val type = object : TypeToken<ErrorResponse>(){}.type
//                val error = Gson().fromJson((it as HttpException).response().errorBody(), type) as ErrorResponse
                dismissLoading()
                showMessage(it.message!!)
            })
        compositeDisposable.add(disposables)
    }
}