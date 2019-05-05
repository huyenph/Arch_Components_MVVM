package com.utildev.compsmvvm.data.remote.response.site

import com.utildev.compsmvvm.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SiteResponse : BaseModel() {
    @SerializedName("items")
    @Expose
    val items: MutableList<SiteItemResponse>? = null
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean? = null
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int? = null
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int? = null
}