package com.example.gallery.data.remote

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id") val id: Int,
    @SerializedName("author") val author: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val imageThumbnailUrl: String,
    @SerializedName("download_url") val fullImageUrl: String,
)
