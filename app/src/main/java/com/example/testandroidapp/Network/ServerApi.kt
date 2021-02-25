package com.example.testandroidapp.Network

import retrofit2.http.GET
import retrofit2.Call

interface ServerApi {

    @GET("map")
    fun funCategories():Call<List<myCategory>>

//    @GET("map")
//    fun funObject():Call<List<myObject>>

}