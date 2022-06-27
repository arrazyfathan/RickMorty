package com.arrazyfathan.rickmorty.utils

import com.google.gson.annotations.SerializedName

open class CommonResponse(
    @SerializedName("statusCode")
    val statusCode: Int = 500,
    @SerializedName("message")
    val message: String? = "Server Error",
    @SerializedName("error")
    val error: MutableList<String>? = ArrayList()
)