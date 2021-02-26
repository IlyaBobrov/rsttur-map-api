package com.example.testandroidapp.Api.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
/*
    private var sSoleInstance: Example? = null

    //if there is no instance available... create new one
    val instance: Example?
        get() {
            if (sSoleInstance == null) { //if there is no instance available... create new one
                sSoleInstance = Example
            }
            return sSoleInstance
        }
*/

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


/*
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
}*/
