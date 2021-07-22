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


   // private lateinit var auth: FirebaseAuth
  //  val current = auth.currentUser

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

//    private fun updateUI(firebaseUser: FirebaseUser?) {
//        if (firebaseUser != null) {
//
////            val user = User(firebaseUser.uid, firebaseUser.displayName, firebaseUser.photoUrl.toString())
////            val usersDao = UserDao()
////            usersDao.addUser(user)
//            val db = Firebase.firestore
//            // val docRef = db.collection("member").document(current.toString().phonedb)
//          var user1212:myuser?=null
//            //  if (null == docRef.get()) {
//            // if(null==db.collection("member")."phonedb".get())
//            val user = FirebaseAuth.getInstance().currentUser
//
//            val docRef = db?.collection("member")?.document(user?.uid!!)
//
//            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
//                 user1212= documentSnapshot.toObject(myuser::class.java)
//            }
//
//            if (user1212?.phoneid == null) {
//                val intent = Intent(this, StudentDashboard::class.java)
//                startActivity(intent)
//                //val mainActivityIntent = Intent(this, MainActivity::class.java)
//                // startActivity(mainActivityIntent)
//
//            } else {
//                val intent = Intent(this, instituteDashboard::class.java)
//                startActivity(intent)
//            }
//
//        }
//    }

    fun studentloginfunc(view: View) {

        val intent = Intent(this, studentLogin::class.java)
        startActivity(intent)
    }

    fun instituteloginfunc(view: View) {
        val intent = Intent(this, institueLogin::class.java)
        startActivity(intent)
    }

    fun registerfun(view: View) {

        val intent = Intent(this, registerpage::class.java)
        startActivity(intent)
    }

    fun driverlogin(view: View) {

        val intent = Intent(this, driverlogin::class.java)
        startActivity(intent)
    }
}
