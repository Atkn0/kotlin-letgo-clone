package com.example.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.databinding.RecyclerRowBinding

class myListingRecyclerViewAdapter(val liste: ArrayList<String> = ArrayList()): RecyclerView.Adapter<myListingRecyclerViewAdapter.MyListingHolder>() {


    class MyListingHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListingHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyListingHolder(binding)
    }

    override fun onBindViewHolder(holder: MyListingHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}