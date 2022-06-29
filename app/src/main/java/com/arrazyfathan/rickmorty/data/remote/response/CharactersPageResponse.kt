package com.arrazyfathan.rickmorty.data.remote.response

import com.google.gson.annotations.SerializedName

data class CharactersPageResponse(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val results: List<SingleCharacterResponse> = emptyList()
) {
    data class Info(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("pages")
        val pages: Int = 0,
        @SerializedName("next")
        val next: String? = null,
        @SerializedName("prev")
        val prev: String? = null
    )
}