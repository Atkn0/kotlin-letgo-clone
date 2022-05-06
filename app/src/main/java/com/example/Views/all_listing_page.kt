package com.example.Views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Adapter.recyclerViewAdapter
import com.example.Models.PostModel
import com.example.productapp.R
import com.example.productapp.databinding.ActivityAllListingPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class all_listing_page : AppCompatActivity() {

    lateinit var binding: ActivityAllListingPageBinding
    lateinit var auth:FirebaseAuth
    lateinit var fireStore:FirebaseFirestore
    lateinit var postModelList:ArrayList<PostModel>
    lateinit var adapter: recyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllListingPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()


        fireStore = FirebaseFirestore.getInstance()
        getDataFromFirestore()

        postModelList= ArrayList()

        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = recyclerViewAdapter(postModelList)
        binding.RecyclerView.adapter = adapter


    }

    fun logOutButton(){

        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.allpagemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addProduct){
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }

        if (item.itemId == R.id.logOut){
            logOutButton()
        }

        if (item.itemId == R.id.myListing){
            val intent = Intent(this,MyListing::class.java)
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun getDataFromFirestore(){

        fireStore.collection("Products").addSnapshotListener { snapshot, error ->

            if (error != null){
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else{
                if (snapshot!=null){

                    postModelList.clear()

                    val documents = snapshot.documents
                    for (document in documents){
                        val productName = document.get("productName") as String
                        val productPrice = document.get("productPrice") as String
                        val email = document.get("email") as String
                        val downloadUri = document.get("downloadUri") as String

                        val postModel =PostModel(email = email,productName = productName,productPrice = productPrice,downloadUri = downloadUri)
                        postModelList.add(postModel)
                    }

                    adapter.notifyDataSetChanged()

                }
            }

        }


    }


}


