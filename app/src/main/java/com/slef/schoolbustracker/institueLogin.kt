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

class institueLogin : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institue_login)
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser);
            // ...// reload();
        }
    }

    fun loginfunction(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.editText1)
        val password = editText1.text.toString()


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "Authentication sucesfull , You are in !.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //  Log.w(TAG, "signInWithEmail:failure", task.exception)
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
            val intent = Intent(this,instituteDashboard::class.java)
            startActivity(intent)
            //val mainActivityIntent = Intent(this, MainActivity::class.java)
            // startActivity(mainActivityIntent)

        }

    }

}
