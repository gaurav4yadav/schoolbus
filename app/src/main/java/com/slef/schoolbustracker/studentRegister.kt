package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class studentRegister : AppCompatActivity() {


    private   lateinit var auth: FirebaseAuth
    val db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_register)



        auth = Firebase.auth

    }
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            updateUI(currentUser)
//        }
//    }

    fun register(view: View) {


        val editText = findViewById<EditText>(R.id.editText1)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.editText2)
        val password = editText1.text.toString()

        val editText2 = findViewById<EditText>(R.id.editText3)
        val unique = editText2.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val data = hashMapOf(
                        "emaildb" to email,
                        "passworddb" to password,
                        "uniquedb" to unique,
                        "phonedb" to null
                    )

                    db.collection("member")
                        .add(data)
                        .addOnSuccessListener {
                         //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                            Toast.makeText(baseContext, "Added to DATABASE",
                                Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            //Log.w(TAG, "Error adding document", e)
                            Toast.makeText(baseContext, "scomething went wrong as always",
                                Toast.LENGTH_SHORT).show()
                        }


                    // Sign in success, update UI with the signed-in user's information
               //     Log.d(TAG, "createUserWithEmail:success")

                    Toast.makeText(baseContext, "scccessfully Registered ! Please LOGIN",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                   // Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }


    }

    private fun updateUI(firebaseUser: FirebaseUser?) {
        if(firebaseUser != null) {

//            val user = User(firebaseUser.uid, firebaseUser.displayName, firebaseUser.photoUrl.toString())
//            val usersDao = UserDao()
//            usersDao.addUser(user)
            val intent = Intent(this,studentLogin::class.java)
            startActivity(intent)
            //val mainActivityIntent = Intent(this, MainActivity::class.java)
            // startActivity(mainActivityIntent)

        }
        else
        {
            Toast.makeText(baseContext, "scomething went wrong",
                Toast.LENGTH_SHORT).show()
        }
    }



}

