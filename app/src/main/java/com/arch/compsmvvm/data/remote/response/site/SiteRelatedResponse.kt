package com.arch.compsmvvm.data.remote.response.site

import com.arch.compsmvvm.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SiteRelatedResponse : BaseModel() {
    @SerializedName("relation")
    @Expose
    val relation: String? = null
        get() = field ?: ""
    @SerializedName("api_site_parameter")
    @Expose
    val apiSiteParameter: String? = null
        get() = field ?: ""
    @SerializedName("site_url")
    @Expose
    val siteUrl: String? = null
        get() = field ?: ""
    @SerializedName("name")
    @Expose
    val name: String? = null
        get() = field ?: ""
}