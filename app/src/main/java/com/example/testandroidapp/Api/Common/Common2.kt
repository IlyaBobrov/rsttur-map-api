package com.example.testandroidapp.Api.Common

import com.example.testandroidapp.Api.Interface.RetrofitServices
import com.example.testandroidapp.Api.Retrofit.RetrofitClient

object Common2 {
    private val BASE_URL: String = "https://rsttur.ru/api/base-app/"
    val retrofitServicesCategory: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}