package com.example.gallery.data.remote

import com.example.gallery.data.repository.GalleryImageRepositoryImpl
import com.example.gallery.domain.repository.GalleryImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGalleryImageRepository(
        impl: GalleryImageRepositoryImpl
    ): GalleryImageRepository
}