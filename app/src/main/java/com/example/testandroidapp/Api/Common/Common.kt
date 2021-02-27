package com.example.testandroidapp.Api.Common

import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Retrofit.RetrofitClient

object Common {
    private val BASE_URL: String = "https://rsttur.ru/api/base-app/"
    val retrofitServicesCategory: RetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitInterface::class.java)
}