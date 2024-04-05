package com.example.githubapiapp.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.githubapiapp.Data.response.Follow
import com.example.githubapiapp.databinding.DataRowBinding


class FollowerAdapter (private val listFollow: List<Follow>): RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DataRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(followersGithub: Follow) {
            Glide.with(itemView.context).load(followersGithub.avatarUrl).into(binding.profileImage)
            binding.tvItemName.text = followersGithub.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listFollow.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFollow[position])
    }


}