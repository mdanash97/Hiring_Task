package com.example.hiringtask.view

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiringtask.databinding.VideoItemBinding

class VideoDiffUtil : DiffUtil.ItemCallback<Video>(){
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }
}
class VideoViewHolder(var holderBinding: VideoItemBinding):  RecyclerView.ViewHolder(holderBinding.root)

class VideosAdaptor : ListAdapter<Video, VideoViewHolder>(VideoDiffUtil()){

    lateinit var adaptorBinding: VideoItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater : LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        adaptorBinding = VideoItemBinding.inflate(inflater,parent,false)
        return VideoViewHolder(adaptorBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentObj = getItem(position)
        Glide.with(holder.itemView.context)
            .load(currentObj.uri)
            .into(holder.holderBinding.imageView2)
    }
}
data class Video(
    val id:Long,
    val name: String,
    val uri: Uri
)