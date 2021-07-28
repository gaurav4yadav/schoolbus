package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess

//        var myemail:String?=null
//        val user = Firebase.auth.currentUser
//        user?.let {
//
//             myemail = user.email
//        }
//        Toast.makeText(baseContext, "$myemail",Toast.LENGTH_SHORT).show()

public var code=""
class StudentDashboard : AppCompatActivity() {

    val database = Firebase.database
var lt=""
 var lg=""
    var lat=""
    var lon=""

    var save1=""
    var save2=""

    val myRef = FirebaseDatabase.getInstance().getReference("driver")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)










    }

    private var backPressedTime:Long = 0
    lateinit var backToast:Toast
    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            finishAffinity()
            exitProcess(0)
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }


    fun startdata(view: View) {

        val drivercode = findViewById<EditText>(R.id.editText)
        code = drivercode.text.toString()
        

        val path="driver"+code
        
        if(code.isEmpty()) {

           drivercode.requestFocus();
            drivercode.setError("FIELD CANNOT BE EMPTY")
        }
        else {
            //Toast.makeText(baseContext,"$path",Toast.LENGTH_LONG).show()

//        myRef.child(code).get()
//            .addOnSuccessListener {
//
//                if(it.exists())
//                {
//                     lat=it.child("latitute").value.toString()
//                     lon=it.child("longitude").value.toString()
//
//                    save1=lat
//                    save2=lon
//                   // Toast.makeText(baseContext,"$lat",Toast.LENGTH_SHORT).show()
//
//                    //Toast.makeText(baseContext,"$lon",Toast.LENGTH_SHORT).show()
//
//                }
//                else
//                {
//Toast.makeText(baseContext,"failed",Toast.LENGTH_LONG).show()
//                }
//            }
            // Toast.makeText(baseContext,"tesing",Toast.LENGTH_SHORT).show()
            //  Toast.makeText(baseContext,"$save1",Toast.LENGTH_LONG).show()
            //   Toast.makeText(baseContext,"$lon",Toast.LENGTH_LONG).show()


            myRef.child(code).addValueEventListener(object : ValueEventListener {
                fun onEvent(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(dc::class.java)
                    lt = value?.latitute.toString()
                    lg = value?.longitude.toString()





                    Toast.makeText(baseContext, "$lt", Toast.LENGTH_LONG).show()
                    Toast.makeText(baseContext, "$lg", Toast.LENGTH_LONG).show()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {


                    val value = dataSnapshot.getValue(dc::class.java)
                    lt = value?.latitute.toString()
                    lg = value?.longitude.toString()
                    Toast.makeText(baseContext, "$lt", Toast.LENGTH_LONG).show()
                    Toast.makeText(baseContext, "$lg", Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Toast.makeText(
                        baseContext,
                        "Failed to read value.$error.toException()",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })


            val intent = Intent(this, studentmap::class.java)
            // intent.putExtra("mycode",code)
            startActivity(intent)
        }
    }


}


