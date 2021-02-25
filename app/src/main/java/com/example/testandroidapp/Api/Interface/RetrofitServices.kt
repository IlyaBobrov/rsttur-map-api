package com.example.testandroidapp.Api.Interface

import com.example.testandroidapp.Api.Model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices : RetrofitInterface {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}