package com.example.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productapp.R
import com.example.productapp.databinding.ActivityMyListingBinding

class MyListing : AppCompatActivity() {

    lateinit var binding: ActivityMyListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMyListingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}