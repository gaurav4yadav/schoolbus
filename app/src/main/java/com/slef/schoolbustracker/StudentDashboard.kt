package com.slef.schoolbustracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class StudentDashboard : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
        var myemail:String?=null
        val user = Firebase.auth.currentUser
        user?.let {

             myemail = user.email
        }
        Toast.makeText(baseContext, "$myemail",Toast.LENGTH_SHORT).show()
    }
}