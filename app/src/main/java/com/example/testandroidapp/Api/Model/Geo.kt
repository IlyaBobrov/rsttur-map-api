package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geo {
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null
}
