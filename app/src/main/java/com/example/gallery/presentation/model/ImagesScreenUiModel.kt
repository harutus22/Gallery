package com.example.gallery.presentation.model

import com.example.gallery.domain.model.GalleryImage

data class ImagesScreenUiModel(
    val isLoading: Boolean = false,
    val isDialogOpened: Boolean = false,
    val imagesList: List<GalleryImage> = emptyList(),
    val imageUrlForDialog: String? = null
)
