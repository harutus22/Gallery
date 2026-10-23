package com.example.gallery.data.repository

import com.example.gallery.data.remote.ImagesApiService
import com.example.gallery.domain.model.GalleryImage
import com.example.gallery.domain.repository.GalleryImageRepository
import javax.inject.Inject

class GalleryImageRepositoryImpl @Inject constructor(
    private val imagesApiService: ImagesApiService
): GalleryImageRepository {
    override suspend fun getImages(page: Int): List<GalleryImage> {
        val imageList = imagesApiService.getImages(page = page)
        return imageList.map { imagesDto ->
                val newWidth = 200
            val newHeight = 200
            val thumbnailUrl = imagesDto.fullImageUrl.replace(Regex("/\\d+/\\d+$"), "/$newWidth/$newHeight")
                GalleryImage(thumbnailUrl, imagesDto.fullImageUrl)
            }
    }
}