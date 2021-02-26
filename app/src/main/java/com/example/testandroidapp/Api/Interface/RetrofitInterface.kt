package com.example.testandroidapp.Api.Interface

import com.example.testandroidapp.Api.Model.Category
import com.example.testandroidapp.Api.Model.Example
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    /*@GET("map")
    fun getCategory(): Call<MutableList<Category>>*/

    @GET("map")
    fun getExample(): Call<Example>

    /*@FormUrlEncoded
    @GET("map")
    fun getCategory(
        @Field*/
}