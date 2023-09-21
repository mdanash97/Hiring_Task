package com.example.hiringtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiringtask.data.repository.RepositoryInterface
import com.example.hiringtask.view.Image
import com.example.hiringtask.view.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel(){
    private val _images : MutableLiveData<List<Image>> = MutableLiveData()
    val images :LiveData<List<Image>> = _images

    private val _videos : MutableLiveData<List<Video>> = MutableLiveData()
    val videos :LiveData<List<Video>> = _videos

    fun getImages(){
        viewModelScope.launch {
            repository.getImages().collect{
                _images.value = it
            }
        }
    }

    fun getVideos(){
        viewModelScope.launch {
            repository.getVideos().collect{
                _videos.value = it
            }
        }
    }
}