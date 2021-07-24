package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.slef.schoolbustracker.models.myuser

class MainActivity : AppCompatActivity() {


    //private lateinit var auth: FirebaseAuth
    //val current = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // auth = Firebase.auth
    }


//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            updateUI(currentUser)
//            // ...// reload();
//        }
//    }


    fun studentloginfunc(view: View) {

        val intent = Intent(this, studentLogin::class.java)
        startActivity(intent)
    }


    fun driverlogin(view: View) {

        val intent = Intent(this, DriverLoginNew::class.java)
        startActivity(intent)
    }
//
//private fun updateUI(firebaseUser: FirebaseUser?) {
//        var myemail: String? = null
//        val user = Firebase.auth.currentUser
//        user?.let {
//
//            myemail = user.email
//        }
//
//        val db = Firebase.firestore
//
//
//
//        db.collection("driver")
//            .whereEqualTo("emaildb", myemail.toString())
//            .get()
//            .addOnSuccessListener {
//                if (it.size() == 0) {
//
//
//                    val intent = Intent(this, driverDashboard::class.java)
//                    startActivity(intent)
//                }
//                else
//                {
//                    val intent = Intent(this, StudentDashboard::class.java)
//                    startActivity(intent)
//                }
//            }
//
//
//
//    }


}
