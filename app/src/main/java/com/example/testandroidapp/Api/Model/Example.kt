package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("error")
    @Expose
    var error: Any? = null

    @SerializedName("time")
    @Expose
    var time: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
}