package com.example.hiringtask.view

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiringtask.databinding.FragmentVideosBinding
import com.example.hiringtask.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment : Fragment() {

    private lateinit var videosBinding: FragmentVideosBinding
    private val viewModel: ViewModel by viewModels()
    private val videosAdaptor by lazy {
        VideosAdaptor()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        videosBinding = FragmentVideosBinding.inflate(inflater,container,false)
        return videosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVideos()
        observeVideos()
        setVideosRV()
    }
    private fun observeVideos(){
        viewModel.videos.observe(viewLifecycleOwner){
            videosAdaptor.submitList(it)
        }
    }

    private fun setVideosRV(){
        videosBinding.vidRV.apply {
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = videosAdaptor
        }
    }


}