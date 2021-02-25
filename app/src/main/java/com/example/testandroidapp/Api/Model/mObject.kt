package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class mObject {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("working_hours")
    @Expose
    var workingHours: List<WorkingHour>? = null
}