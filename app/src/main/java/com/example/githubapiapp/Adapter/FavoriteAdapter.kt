package com.example.githubapiapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapiapp.Data.local.database.Favorite
import com.example.githubapiapp.Data.response.User
import com.example.githubapiapp.databinding.DataRowBinding

class FavoriteAdapter(private val listUser:List<Favorite>): RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallBack
    inner class FavoriteHolder(private val binding: DataRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Favorite){
            Glide.with(itemView.context).load(data.avatarUrl).into(binding.profileImage)
            binding.tvItemName.text = data.name
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(DataRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        var user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

}