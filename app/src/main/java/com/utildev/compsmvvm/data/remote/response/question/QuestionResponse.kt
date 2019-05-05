package com.utildev.compsmvvm.data.remote.response.question

import com.utildev.compsmvvm.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionResponse: BaseModel() {
    @SerializedName("items")
    @Expose
   val items: MutableList<QuestionItemResponse>? = null
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean = false
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int = 0
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int = 0
}