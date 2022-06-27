package com.arrazyfathan.rickmorty.data.remote.response


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)