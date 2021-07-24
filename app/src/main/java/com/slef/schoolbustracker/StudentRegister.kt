package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase





class StudentRegister : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentregister)
        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser);
            // ...// reload();
        }
    }

    fun goon(view: View) {

        val editText = findViewById<EditText>(R.id.editText)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.editText1)
        val password = editText1.text.toString()

        val editText2 = findViewById<EditText>(R.id.editText2)
        val unique = editText2.text.toString()

        val editText3 = findViewById<EditText>(R.id.editText3)
        val phone = editText3.text.toString()


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val data = hashMapOf(
                        "emaildb" to email,
                        "passworddb" to password,
                        "uniquedb" to unique,
                        "phonedb" to phone

                    )

                    db.collection("member")
                        .add(data)
                        .addOnSuccessListener {
                            //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                            Toast.makeText(
                                baseContext, "Student Registered Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val user = auth.currentUser
                            updateUI(user)

                        }
                        .addOnFailureListener {
                            //Log.w(TAG, "Error adding document", e)
                            Toast.makeText(
                                baseContext, "scomething went wrong ",
                                Toast.LENGTH_SHORT
                            ).show()
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                            updateUI(null)

                            // Log.d(TAG, "createUserWithEmail:success")
//                            val user = auth.currentUser
//                            updateUI(user)
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    //  Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null) {
            val intent = Intent(this, studentLogin::class.java)
            startActivity(intent)
        }

    }

}