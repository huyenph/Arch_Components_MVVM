package com.utildev.compsmvvm.data.remote.response.error

import com.utildev.compsmvvm.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse : BaseModel() {
    @SerializedName("error_id")
    @Expose
    val errorId: Int = 0
    @SerializedName("error_message")
    @Expose
    val errorMessage: String? = null
        get() = field ?: ""
    @SerializedName("error_name")
    @Expose
    val errorName: String? = null
        get() = field ?: ""
}