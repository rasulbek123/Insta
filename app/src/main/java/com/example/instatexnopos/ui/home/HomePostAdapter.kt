package com.example.instatexnopos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instatexnopos.data.model.Post
import com.example.instatexnopos.databinding.ItemPostHomeBinding

class HomePostAdapter:RecyclerView.Adapter<HomePostAdapter.HomePostViewHolder>() {
   var models:List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class HomePostViewHolder(private val binding:ItemPostHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun populeatmodel(post:Post){
            Glide.with(binding.root.context)
                .load(post.imageUrl)
                .centerCrop()
                .into(binding.homeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostViewHolder {
       val binding = ItemPostHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePostViewHolder, position: Int) {
        holder.populeatmodel(models[position])
    }

    override fun getItemCount()=models.size
}