package com.slef.schoolbustracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.slef.schoolbustracker.databinding.ActivityStudentmapBinding

class studentmap : AppCompatActivity(), OnMapReadyCallback {
    var lt=""
    var lg=""
    var lat=""
    var lon=""

    var save1=""
    var save2=""


    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityStudentmapBinding
    private lateinit var auth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    var marker: Marker? = null
    var sydney = LatLng(23.2295537,77.392109)

    val database = Firebase.database
    val myRef = FirebaseDatabase.getInstance().getReference("driver")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)





    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        myRef.child(code).addValueEventListener(object : ValueEventListener {
            fun onEvent(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(dc::class.java)
                lt=value?.latitute.toString()
                lg=value?.longitude.toString()



                sydney = LatLng(lt.toDouble(),lg.toDouble())
                if(marker!=null)
                    marker!!.remove()
                marker=map.addMarker(MarkerOptions().position(sydney).title("Bus").icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.iconcrop)))
                map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                map.moveCamera(CameraUpdateFactory.zoomTo(16f))

               // Toast.makeText(baseContext,"$lt",Toast.LENGTH_LONG).show()
              // Toast.makeText(baseContext,"$lg",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val value = dataSnapshot.getValue(dc::class.java)
                lt=value?.latitute.toString()
                lg=value?.longitude.toString()

                sydney = LatLng(lt.toDouble(),lg.toDouble())
                if(marker!=null)
                    marker!!.remove()
               marker=map.addMarker(MarkerOptions().position(sydney).title("Bus").icon(
                   BitmapDescriptorFactory.fromResource(R.drawable.iconcrop)))
                map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                map.moveCamera(CameraUpdateFactory.zoomTo(16f))

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(baseContext, "Failed to read value.$error.toException()", Toast.LENGTH_LONG).show()
            }
        })

    //    val sydney = LatLng(-34.0, 151.0)
   //     map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}