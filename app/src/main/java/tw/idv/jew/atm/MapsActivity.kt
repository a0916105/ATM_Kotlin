package tw.idv.jew.atm

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val REQUEST_LOCATION: Int = 0
    private lateinit var mMap: GoogleMap

    private lateinit var fuseLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fuseLocationClient = LocationServices.getFusedLocationProviderClient(this)

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
        mMap = googleMap

        mMap?.run {
            mMap.uiSettings.isZoomControlsEnabled = true

            setupMap()
        }

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun setupMap() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION)
            return
        }
        mMap.isMyLocationEnabled = true

        mMap.setOnMyLocationButtonClickListener {
            fuseLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.run {
                    val current = LatLng(latitude, longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 17f))
                }
            }
            false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_LOCATION){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setupMap()
            }
        }
    }
}