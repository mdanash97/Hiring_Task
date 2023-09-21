package com.example.hiringtask.data.repository

import com.example.hiringtask.view.Image
import com.example.hiringtask.view.Video
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    suspend fun getVideos() : Flow<List<Video>>
    suspend fun getImages() : Flow<List<Image>>
}