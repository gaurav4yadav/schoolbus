package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class forgetpassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)
        auth = Firebase.auth
    }


    fun forgetfuntion(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val emailAddress  = editText.text.toString()
        Toast.makeText(baseContext, "$emailAddress" ,Toast.LENGTH_LONG).show()

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Email sent." ,Toast.LENGTH_LONG).show()
                }
            }



    }
}