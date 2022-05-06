package com.example.Views


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.productapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null){
            val intent = Intent(this, all_listing_page::class.java)
            startActivity(intent)
            finish()
        }
        
    }


    fun loginButton(view: View){

        val email = binding.emailtext.text.toString()
        val password = binding.passwordText.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

            val intent = Intent(this, all_listing_page::class.java)
            startActivity(intent)
            finish()


        }.addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }


    }

    fun register(view: View){
        val email = binding.emailtext.text.toString()
        val password = binding.passwordText.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

            val intent = Intent(this, all_listing_page::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }






}