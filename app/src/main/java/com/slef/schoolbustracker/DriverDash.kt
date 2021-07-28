package com.slef.schoolbustracker

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_driver_dash.*
import kotlin.system.exitProcess

class DriverDash : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    var marker: Marker? = null
    var sydney = LatLng(23.2295537,77.392109)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dash)
        enablegps()
        var uni:String=""
        var em:String=""
        var nam:String=""

        var database: FirebaseFirestore ?=null
        database = FirebaseFirestore.getInstance()
        var myemail:String?=null
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }
      //  Toast.makeText(baseContext,"$myemail", Toast.LENGTH_LONG).show()




        database.collection("driver").document(myemail.toString().trim()).get()
            .addOnSuccessListener { document->
                if(document!=null)
                {
                    uni=document.getString("uniquedb").toString()
                    em=document.getString("emaildb").toString()
                    nam =document.getString("namedb").toString()



                    etname.setText(uni)
                    etemail.setText(em)
                    etunique.setText(uni)
//

                }
            }

      //  Toast.makeText(baseContext,"$trya", Toast.LENGTH_LONG).show()





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

    fun enablegps() {

        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(2000)
            .setFastestInterval(1000)

        val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(this).checkLocationSettings(settingsBuilder.build())
        result.addOnCompleteListener { task ->

            //getting the status code from exception
            try {
                task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {

                when (ex.statusCode) {

                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {

                        Toast.makeText(this,"GPS IS OFF",Toast.LENGTH_SHORT).show()

                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this,1
                        )
                    } catch (e: IntentSender.SendIntentException) {
                        Toast.makeText(this,"PendingIntent unable to execute request.",Toast.LENGTH_SHORT).show()

                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                        Toast.makeText(
                            this,
                            "Something is wrong in your GPS",
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                }
            }



        }


    }
//    override fun onResume() {
//        super.onResume()
//        checkGPSEnabled()
//    }
//
//   private fun checkGPSEnabled() {
//        val manager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER).not()) {
//            turnOnGPS()
//        }
//    }
//    private fun turnOnGPS() {
//        val request = LocationRequest.create().apply {
//            interval = 2000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//        val builder = LocationSettingsRequest.Builder().addLocationRequest(request)
//        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
//        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
//        task.addOnFailureListener {
//            if (it is ResolvableApiException) {
//                try {
//                    it.startResolutionForResult(requireActivity(), 12345)
//                } catch (sendEx: IntentSender.SendIntentException) {
//                }
//            }
//        }.addOnSuccessListener {
//            //here GPS is On
//        }
//    }


    fun mapstart(view: View) {

        val intent= Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun trackother(view: View) {

        val intent= Intent(this,StudentDashboard::class.java)
        startActivity(intent)
    }
}