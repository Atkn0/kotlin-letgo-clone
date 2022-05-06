package com.example.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.Models.PostModel
import com.example.productapp.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso

class recyclerViewAdapter(val postModelList: ArrayList<PostModel>): RecyclerView.Adapter<recyclerViewAdapter.PostHolder>() {


    class PostHolder(val binding: RecyclerRowBinding) :RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.RecyclerProductPrice.text = postModelList.get(position).productPrice
        holder.binding.RecyclerTextName.text = postModelList.get(position).productName
        Picasso.get().load(postModelList[position].downloadUri).into(holder.binding.RecyclerImageView)

    }

    override fun getItemCount(): Int {
        return postModelList.size
    }
}