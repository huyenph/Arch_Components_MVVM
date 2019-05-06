package com.utildev.compsmvvm.presentation.fragment.question

import android.arch.lifecycle.MutableLiveData
import com.utildev.compsmvvm.data.remote.response.question.QuestionItemResponse
import com.utildev.compsmvvm.data.remote.response.question.QuestionResponse
import com.utildev.compsmvvm.data.repository.Repository
import com.utildev.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class QuestionViewModel(private val repository: Repository): BaseViewModel(repository) {
    var questionLive: MutableLiveData<MutableList<QuestionItemResponse>> = MutableLiveData()

    fun getQuestion(site: String, page: Int, loading: Boolean) {
        if (loading) showLoading()
        apiClient.request(2, object : TypeToken<QuestionResponse>() {}.type, repository.getQuestion(site, page))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val question = Gson().fromJson(response, type) as QuestionResponse
        questionLive.value = question.items
    }
}