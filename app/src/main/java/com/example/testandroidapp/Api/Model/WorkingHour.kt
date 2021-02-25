package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WorkingHour {
    @SerializedName("days")
    @Expose
    var days: List<Int>? = null

    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("to")
    @Expose
    var to: String? = null
}
