package com.example.instatexnopos.ui.profil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instatexnopos.data.model.Post
import com.example.instatexnopos.databinding.ItemPostProfilBinding

class ProfilePostAdapter:RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder>() {
    var models:List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class ProfilePostViewHolder(private val binding:ItemPostProfilBinding):RecyclerView.ViewHolder(binding.root){
        fun populeatmodel(post:Post){
            Glide .with(binding.root.context)
                .load(post.imageUrl)
                .centerCrop()
                .into(binding.postImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostViewHolder {
        val binding = ItemPostProfilBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfilePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        holder.populeatmodel(models[position])
    }

    override fun getItemCount() = models.size
}