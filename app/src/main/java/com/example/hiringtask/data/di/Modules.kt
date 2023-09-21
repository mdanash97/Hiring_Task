package com.example.hiringtask.data.di

import android.content.Context
import com.example.hiringtask.data.GalleryLoader
import com.example.hiringtask.data.repository.Repository
import com.example.hiringtask.data.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GalleryLoaderModule{
    @Provides
    @Singleton
    fun provideGalleryLoader(@ApplicationContext context: Context) : GalleryLoader {
        return GalleryLoader(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Provides
    @Singleton
    fun provideRepository(galleryLoader: GalleryLoader) : RepositoryInterface {
        return Repository(galleryLoader)
    }
}
