package com.example.instatexnopos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instatexnopos.data.model.Post
import com.example.instatexnopos.databinding.ItemPostHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class HomePostAdapter:RecyclerView.Adapter<HomePostAdapter.HomePostViewHolder>() {
  private var onDoubleClicked:(post:Post)->Unit ={}
  fun setOnDoubleClickedListener(onDoubleClicked:(post:Post)->Unit){
      this.onDoubleClicked = onDoubleClicked}

    private var onLiked:(postId:String)->Unit ={}
    fun setOnLikedListener(onLiked:(postId:String)->Unit){
        this.onLiked = onLiked}

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
            //            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//            val dateString = simpleDateFormat.format(post.createdDate)
//            binding.tvCreatedDate.text = String.format("Date:%s",dateString)
            var doubleClick = false
            binding.homeImage.setOnClickListener {
                if(doubleClick){
                    onDoubleClicked.invoke(post)
                    doubleClick = false
                }else{
                    doubleClick = true
                    GlobalScope.launch {
                        delay(500)
                        doubleClick = false
                    }
                }

            }
            binding.btnlike.setOnClickListener {
                onLiked.invoke(post.id)
            }
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