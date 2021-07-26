package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DriverDash : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dash)

        var trya:String=""
        var database: FirebaseFirestore ?=null
        database = FirebaseFirestore.getInstance()
        var myemail:String?=null
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }
        Toast.makeText(baseContext,"$myemail", Toast.LENGTH_LONG).show()
        database.collection("driver").document(myemail.toString().trim()).get()
            .addOnSuccessListener { document->
                if(document!=null)
                {
                    trya=document.getString("uniquedb").toString()


                }
            }

        Toast.makeText(baseContext,"$trya", Toast.LENGTH_LONG).show()
    }


    fun mapstart(view: View) {

        val intent= Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}