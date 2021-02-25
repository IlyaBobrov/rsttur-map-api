package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("geo")
    @Expose
    var geo: Geo? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("objects")
    @Expose
    var objects: List<Object>? = null
}