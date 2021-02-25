package com.example.testandroidapp.Api.Common

import com.example.testandroidapp.Api.Interface.RetrofitServices
import com.example.testandroidapp.Api.Retrofit.RetrofitClient

object Common1 {
    private val BASE_URL: String = "https://www.simplifiedcoding.net/demos/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}