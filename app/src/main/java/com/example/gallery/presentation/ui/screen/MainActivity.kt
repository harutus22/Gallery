package com.example.gallery.presentation.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.gallery.presentation.ui.components.ImageContainer
import com.example.gallery.presentation.ui.components.ImageDialog
import com.example.gallery.presentation.ui.theme.GalleryTheme
import com.example.gallery.presentation.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImagesGridScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImagesGridScreen(
    modifier: Modifier = Modifier,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = {
            viewModel.updateImages()
        },
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(uiState.imagesList) { galleryImage ->
                    ImageContainer(galleryImage.thumbnail) {
                        viewModel.openDialog(galleryImage.fullImage)
                    }
                }
            },
            modifier = modifier.fillMaxSize(),
        )
        if (uiState.isDialogOpened) {
            ImageDialog(uiState.imageUrlForDialog!!) {
                viewModel.closeDialog()
            }
        }
    }

}