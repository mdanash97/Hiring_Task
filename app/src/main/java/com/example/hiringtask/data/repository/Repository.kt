package com.example.hiringtask.data.repository

import com.example.hiringtask.data.GalleryLoader
import com.example.hiringtask.view.Image
import com.example.hiringtask.view.Video
import kotlinx.coroutines.flow.Flow

class Repository(private val galleryLoader: GalleryLoader) : RepositoryInterface{
    override suspend fun getVideos(): Flow<List<Video>> {
        return galleryLoader.loadAllVideos()
    }

    override suspend fun getImages(): Flow<List<Image>> {
        return galleryLoader.loadAllImages()
    }

}