package com.arrazyfathan.rickmorty.utils

import com.google.gson.annotations.SerializedName

data class ApiResult<out T>(
    @SerializedName("data")
    val data: T? = null
) : CommonResponse()
