package com.example.Views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.Models.PostModel
import com.example.productapp.databinding.ActivityAddProductBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AddProduct : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var selectedPicture:Uri
    lateinit var storage: FirebaseStorage
    lateinit var db: FirebaseFirestore
    lateinit var auth:FirebaseAuth
    lateinit var postMap:HashMap<String,Any>
    lateinit var postModelList: ArrayList<PostModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        registerLauncher()
        db = FirebaseFirestore.getInstance()

        postModelList = ArrayList()


    }

     fun onClickImageView(view: View){

        val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intent)


    }


    private fun registerLauncher(){

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->

            if(result.resultCode == RESULT_OK){
                val intentFromResult = result.data
                if (intentFromResult != null){
                    selectedPicture = intentFromResult.data!!
                    binding.imageView.setImageURI(selectedPicture)
                }
            }

        }

    }


    fun AddPostButtonClicked(view: View){


        val uuid = UUID.randomUUID()
        val imageName = "$uuid.jpeg"

        val storage = FirebaseStorage.getInstance()
        val email = auth.currentUser!!.email.toString()
        val productName = binding.PostNameText.text.toString()
        val productPrice = binding.productPrice.text.toString()
        val storageRef = storage.reference.child("${email}/$imageName")
        postMap = HashMap()


        fun Addingfirestore(){

            storageRef.putFile(selectedPicture).addOnSuccessListener {

                val uploadImageUri = storage.reference.child(email).child(imageName)
                uploadImageUri.downloadUrl.addOnSuccessListener { result ->

                    val uri = result
                    postMap.put("downloadUri",uri)
                    postMap.put("email",email)
                    postMap.put("productName",productName)
                    postMap.put("productPrice",productPrice)

                    db.collection("Products").add(postMap).addOnSuccessListener {


                        finish()

                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                }






            }.addOnFailureListener{
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }



        }




        if(selectedPicture != null){

            Addingfirestore()


        }


    }




}