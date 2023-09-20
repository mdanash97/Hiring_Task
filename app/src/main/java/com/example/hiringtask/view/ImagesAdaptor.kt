package com.example.hiringtask.view

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiringtask.databinding.ImageItemBinding

class ImagesDiffUtil : DiffUtil.ItemCallback<Image>(){
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

}
class ImageViewHolder(var holderBinding:ImageItemBinding):  RecyclerView.ViewHolder(holderBinding.root)

class ImagesAdaptor : ListAdapter<Image, ImageViewHolder>(ImagesDiffUtil()){

    lateinit var adaptorBinding: ImageItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater : LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        adaptorBinding = ImageItemBinding.inflate(inflater,parent,false)
        return ImageViewHolder(adaptorBinding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentObj = getItem(position)
        Glide.with(holder.itemView.context)
            .load(currentObj.uri)
            .into(holder.holderBinding.imageView)
    }

}

data class Image(
    val id:Long,
    val name: String,
    val uri: Uri
)