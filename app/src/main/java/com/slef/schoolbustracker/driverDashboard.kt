package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class driverDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dashboard)
    }

    fun instituteregisterfun(view: View) {

        val intent= Intent(this,instituteRegister::class.java)
        startActivity(intent)
    }
    fun studentregisterfun(view: View) {

        val intent =Intent(this,studentRegister::class.java)
        startActivity(intent)

    }
}
