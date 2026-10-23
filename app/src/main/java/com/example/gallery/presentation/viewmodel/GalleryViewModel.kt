package com.example.gallery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.domain.repository.GalleryImageRepository
import com.example.gallery.presentation.model.ImagesScreenUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

private const val PAGE_LIMIT = 11
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: GalleryImageRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(ImagesScreenUiModel())
    val uiState: StateFlow<ImagesScreenUiModel> = _uiState

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val result = repository.getImages(Random.nextInt(PAGE_LIMIT))
            _uiState.update {
                it.copy(
                    imagesList = result,
                    isLoading = false
                )
            }
        }
    }

    fun openDialog(url: String) {
        _uiState.update {
            it.copy(isDialogOpened = true, imageUrlForDialog = url)
        }
    }

    fun closeDialog() {
        _uiState.update {
            it.copy(isDialogOpened = false, imageUrlForDialog = null)
        }
    }

    fun updateImages() {
        getImages()
    }
}