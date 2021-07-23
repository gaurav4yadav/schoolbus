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
import com.google.firebase.ktx.Firebase

class studentLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
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
    fun TrackBus(view: View) {



        val editText = findViewById<EditText>(R.id.editText)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.editText1)
        val password = editText1.text.toString()


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext, " !!! Welcome !!!!.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //  Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }
    private fun updateUI(firebaseUser: FirebaseUser?) {
        if(firebaseUser != null) {
            val intent= Intent(this,StudentDashboard::class.java)
            startActivity(intent)
        }

    }








    fun gotoregisterationfun(view: View) {

        val intent=Intent(this,StudentRegister::class.java)
        startActivity(intent)
    }


}

