package com.example.hiringtask.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiringtask.databinding.FragmentImagesBinding
import com.example.hiringtask.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private val imagesAdaptor by lazy{
        ImagesAdaptor()
    }
    private lateinit var imagesBinding: FragmentImagesBinding
    private val viewModel:ViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        imagesBinding = FragmentImagesBinding.inflate(inflater,container,false)
        return imagesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!permissions()){
            askForPermission()
        }

        viewModel.getImages()
        observeImages()
        setImagesRV()

    }

    private fun observeImages(){
        viewModel.images.observe(viewLifecycleOwner){
            imagesAdaptor.submitList(it)
        }
    }

    private fun setImagesRV(){
        imagesBinding.imgRV.apply {
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = imagesAdaptor
        }
    }


    private fun askForPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
    }

    private fun permissions(): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                viewModel.getImages()
            }
        }else{
            askForPermission()
        }
    }
}