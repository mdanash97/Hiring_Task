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

    private var videosBinding: FragmentVideosBinding? = null
    private val binding get() = videosBinding!!
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVideos()
        observeVideos()
        setVideosRV()
    }
    private fun observeVideos(){
        viewModel.videos.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.emptyTV.visibility = View.VISIBLE
            }else{
                binding.emptyTV.visibility = View.GONE
                videosAdaptor.submitList(it)
            }
        }
    }

    private fun setVideosRV(){
        binding.vidRV.apply {
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = videosAdaptor
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videosBinding = null
    }
}