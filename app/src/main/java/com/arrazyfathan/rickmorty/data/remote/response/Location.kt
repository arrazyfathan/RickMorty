package com.arrazyfathan.rickmorty.data.remote.response


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)