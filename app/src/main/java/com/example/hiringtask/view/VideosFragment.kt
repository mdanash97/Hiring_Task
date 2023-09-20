package com.example.hiringtask.view

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiringtask.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    lateinit var videosAdaptor: VideosAdaptor
    private lateinit var videosBinding: FragmentVideosBinding
    var videos = mutableListOf<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        videosBinding = FragmentVideosBinding.inflate(inflater,container,false)
        return videosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAllVideos()

        videosAdaptor = VideosAdaptor()
        videosAdaptor.submitList(videos)
        videosBinding.vidRV.apply {
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = videosAdaptor
        }
    }

    private fun loadAllVideos() {
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val cursor = requireActivity().contentResolver.query(
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
    }
}