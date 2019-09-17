package co.anamika.deliveryapp.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import co.anamika.deliveryapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_delivery_detail.*

class DeliveryDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat: Double = -34.0
    private var lng: Double = 151.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_detail)
        supportActionBar?.title = "Delivery Details"

        val locationPermission = ContextCompat.checkSelfPermission(this.baseContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (locationPermission == PackageManager.PERMISSION_DENIED) {
            requestLocationPermission(this)
        }

        val desc: String? = intent.getStringExtra("desc")
        val url: String? = intent.getStringExtra("imageUrl")
        val address: String? = intent.getStringExtra("address")
        lat = intent.getDoubleExtra("lat", -34.0)
        lng = intent.getDoubleExtra("lng", 151.0)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        deliveryDiscription.text = "$desc at $address"
        Picasso.with(this)
            .load(url)
            .resize(300, 200)
            .centerCrop()
            .into(image)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val mLocation = LatLng(lat, lng)
        mMap.addMarker(MarkerOptions().position(mLocation).title("Marker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation))
        mMap.setMinZoomPreference(20.0f)
    }

     private fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 7)
    }
}
