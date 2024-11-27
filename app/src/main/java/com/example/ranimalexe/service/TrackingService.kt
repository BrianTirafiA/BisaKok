package com.example.ranimalexe.service

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import androidx.core.app.ActivityCompat
import android.os.Looper
import android.content.pm.PackageManager


class TrackingService : Service() {

    private val binder = LocalBinder()
//    private val _currentLocation = MutableLiveData<Location>()
//    val currentLocation: LiveData<Location> get() = _currentLocation

    private val _totalDistance = MutableLiveData<Float>()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    private var previousLocation: Location? = null // Menyimpan lokasi sebelumnya
    private var counter : Int = 0
    private var totalDistance : Float = 0.0f

    var distance : Float = 0.0f

    override fun onCreate() {
        super.onCreate()
        Log.d("Tracker", "Service started")

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Set up location request
        locationRequest = LocationRequest.create().apply {
            interval = 500 // Update setiap 0.5 detik
            fastestInterval = 200 // Interval minimum antara update
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY // Menggunakan akurasi tinggi
        }

        locationCallback = object : LocationCallback() {
            // Corrected method signature
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.let {
                    val currentLocation = it.lastLocation
                    Log.d("Tracker", "Current Location: ${currentLocation.latitude}, ${currentLocation.longitude}")

                    // Jika previousLocation ada, hitung jaraknya
                    previousLocation?.let { previousLoc ->
                        distance = previousLoc.distanceTo(currentLocation)
                        Log.d("Tracker", "Distance: $distance meters") // Menampilkan jarak dalam meter
                    }

                    totalDistance += distance
                    Log.d("Tracker", "Total Distance: $totalDistance")
                    counter++
                    Log.d("Tracker", "Counter: $counter")

                    // Kirim lokasi terkini ke LiveData
                    _totalDistance.postValue(totalDistance)

                    // Simpan lokasi saat ini sebagai previousLocation untuk update berikutnya
                    previousLocation = currentLocation
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLocationUpdates()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    // Binder untuk mengakses TrackingService
    inner class LocalBinder : Binder() {
        fun getService(): TrackingService = this@TrackingService
    }

    fun getTotalDistanceLiveData(): LiveData<Float> {
        return _totalDistance
    }
}
