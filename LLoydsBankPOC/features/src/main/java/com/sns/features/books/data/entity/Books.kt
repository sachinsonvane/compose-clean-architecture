package com.sns.features.books.data.entity

import com.google.gson.annotations.SerializedName

data class Books(
    @SerializedName("title") val title: String,
    @SerializedName("originalTitle") val originalTitle: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("cover") val cover: String
)
