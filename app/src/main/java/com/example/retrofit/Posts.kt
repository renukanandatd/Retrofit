package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class Posts(
    val userId : Int,
    val id : Int,
    val title : String,
    @SerializedName("body")
    val subTitle : String
) {
}