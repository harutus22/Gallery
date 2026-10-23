package com.example.gallery.domain.repository

import com.example.gallery.domain.model.GalleryImage

interface GalleryImageRepository {
    suspend fun getImages(page: Int): List<GalleryImage>
}