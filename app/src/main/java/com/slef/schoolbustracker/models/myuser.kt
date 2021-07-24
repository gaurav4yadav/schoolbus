package com.slef.schoolbustracker.models

import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth

val user = auth.currentUser
class myuser {



    var qmail = ""
    var gid=""
    var name=""
    var passwordid = ""
    var uniqueid =""
 public   var phoneid =""

    constructor(email:String ,  password:String , uniqueid:String, phoneid:String ,gid:String,name:String)
    {
        qmail=email
        passwordid=password
        this.uniqueid=uniqueid
        this.phoneid=phoneid
        //this.gid =user?.uid.toString()
    }
constructor()
{

}
}