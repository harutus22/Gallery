package com.example.gallery.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApiService {
    @GET("list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 30
    ): List<ImageDto>
}