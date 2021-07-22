package com.slef.schoolbustracker.models

class myuser {

    var gmail = ""
    var passwordid = ""
    var uniqueid =""
 public   var phoneid =""

    constructor(email:String ,  password:String , uniqueid:String, phoneid:String)
    {
        gmail=email
        passwordid=password
        this.uniqueid=uniqueid
        this.phoneid=phoneid
    }

}