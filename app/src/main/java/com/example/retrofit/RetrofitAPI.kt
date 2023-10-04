package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/posts")
    fun getAllPosts() : Call<List<Posts>>
}