package com.slef.schoolbustracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Tasks.await
import com.google.common.util.concurrent.AsyncFunction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_studentregister.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class DriverRegister : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var database=       FirebaseFirestore.getInstance()

    private val db = Firebase.firestore
var check=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driverregister)

        auth = Firebase.auth




    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser)
            // ...// reload();
        }
    }

//    private suspend  fun checking (ok:String):Int {
//
//var re=0




//}
//return re
//    }



    fun adddriverfun(view: View) {

       check = 0

        val editText = findViewById<EditText>(R.id.editText)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.editText1)
        val password = editText1.text.toString()

        val editText2 = findViewById<EditText>(R.id.editText2)
        val name = editText2.text.toString()

        val editText3 = findViewById<EditText>(R.id.editText3)
        val unique = editText3.text.toString()

//



                //Thread.sleep(5000)
                // }

           //     Toast.makeText(baseContext, "here $check", Toast.LENGTH_SHORT).show()



//        GlobalScope.launch()
//        {
//            delay(3000)
//        }



                if (email.isEmpty()) {
                    editText.requestFocus()
                    editText.error = "FIELD CANNOT BE EMPTY"
                } else if (name.isEmpty()) {
                    editText2.requestFocus()
                    editText2.error = "FIELD CANNOT BE EMPTY"
                } else if (unique.isEmpty()) {
                    editText3.requestFocus()
                    editText3.error = "Phone no. must be 10 digit long "
                } else if (password.length < 8) {
                    editText1.requestFocus()
                    editText1.error = "length should be larger than 8 digits"
                } else {
                    progressBar.visibility = View.VISIBLE

                    database.collection("driver").whereEqualTo("uniquedb", unique).get()
                        .addOnSuccessListener{ document ->

                            if (document.size()>0) {

                                editText3.requestFocus()
                                editText3.error = "already used , Try another"
                                progressBar.visibility = View.GONE


                            } else
                            {

                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(this) { task ->
                                        if (task.isSuccessful) {
                                            // Sign in success, update UI with the signed-in user's information
                                            val data = hashMapOf(
                                                "emaildb" to email,
                                                "passworddb" to password,
                                                "uniquedb" to unique,
                                                "namedb" to name

                                            )
                                            var email = ""
                                            val useras = auth.currentUser
                                            if (useras !== null) {

                                                email = useras.email.toString()
                                            }

                                            db.collection("driver").document(email)
                                                .set(data)
                                                .addOnSuccessListener {
                                                    //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                                                    Toast.makeText(
                                                        baseContext, "Registered Successfully",
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


                                                    // Log.d(TAG, "createUserWithEmail:success")
                                                    val user = auth.currentUser
                                                    updateUI(user)
                                                }
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            //  Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                            Toast.makeText(
                                                baseContext,
                                                "Authentication failed.",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            updateUI(null)
                                        }
                                    }
                            }

                        } .addOnFailureListener { exception ->
                            Toast.makeText(baseContext, "faield $exception", Toast.LENGTH_LONG).show()
                        }

                        }












                              }


        private fun updateUI(firebaseUser: FirebaseUser?) {
            if (firebaseUser != null) {
                val intent = Intent(this,DriverLoginNew::class.java)
                startActivity(intent)
            }
            else
            {
                progressBar.visibility = View.GONE
            }

        }


}




