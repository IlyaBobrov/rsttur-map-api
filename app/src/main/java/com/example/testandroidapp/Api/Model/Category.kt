package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null
}
