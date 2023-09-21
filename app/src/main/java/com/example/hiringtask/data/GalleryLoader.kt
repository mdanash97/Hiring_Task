package com.example.hiringtask.data

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.example.hiringtask.view.Image
import com.example.hiringtask.view.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryLoader(private val context: Context) {

    fun loadAllImages() : Flow<List<Image>> = flow{
        val images = mutableListOf<Image>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use {cursor->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                images.add(Image(id,name,uri))
            }
        }
        emit(images)
    }

    fun loadAllVideos() : Flow<List<Video>> = flow {
        val videos = mutableListOf<Video>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val cursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use {cursor->
            val idColumn = cursor.getColumnIndex(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)

            while (cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                videos.add(Video(id,name,uri))
            }
        }
        emit(videos)
    }
}