package com.example.ranimalexe.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.ranimalexe.R
import com.example.ranimalexe.service.TrackingService
import com.example.ranimalexe.viewmodel.RunningViewModel
import android.widget.LinearLayout
import android.location.Location


class RunningActivity : AppCompatActivity() {

    private val runningViewModel: RunningViewModel by viewModels()
    private var trackingService: TrackingService? = null
    private var isServiceBound = false

    // ServiceConnection untuk menghubungkan dengan TrackingService
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TrackingService.LocalBinder
            trackingService = binder.getService()
            isServiceBound = true
            // Observe data lokasi yang dikirim oleh TrackingService dan update ViewModel
            runningViewModel.observeDistanceFromService(trackingService!!)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            trackingService = null
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navbar)

        // Menyesuaikan padding sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Memuat fragment default
        if (savedInstanceState == null) {
            loadFragment(fragment_home())  // Ganti dengan fragment default yang ingin ditampilkan
        }

        // Menyiapkan pemanggilan fragment
        val navProfile = findViewById<LinearLayout>(R.id.nav_profile)
        navProfile.setOnClickListener { loadFragment(fragment_profile()) }
        
        val navEvent = findViewById<LinearLayout>(R.id.nav_event)
        navEvent.setOnClickListener { loadFragment(fragment_event()) }

        val navShop = findViewById<LinearLayout>(R.id.nav_shop)
        navShop.setOnClickListener { loadFragment(fragment_shop()) }

        val navWardrobe = findViewById<LinearLayout>(R.id.nav_wardrobe)
        navWardrobe.setOnClickListener { loadFragment(fragment_wardrobe()) }

        val navHome = findViewById<LinearLayout>(R.id.nav_home)
        navHome.setOnClickListener { loadFragment(fragment_home()) }

//        // Start and bind TrackingService
//        val serviceIntent = Intent(this, TrackingService::class.java)
//        startService(serviceIntent)
//        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)

//        // Observasi jarak di ViewModel
//        runningViewModel.distance.observe(this, Observer { totalDistance ->
//            // Mendapatkan fragment_home dan memperbarui jarak
//            val fragment = supportFragmentManager.findFragmentByTag(fragment_home::class.java.simpleName)
//            if (fragment is fragment_home) {
//                fragment.updateDistance(totalDistance)
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main, fragment, fragment_home::class.java.simpleName)  // Pastikan tag fragment yang benar
        transaction.addToBackStack(null)  // Menambahkan ke back stack untuk navigasi kembali
        transaction.commit()
    }

    private fun calculateDistance(location: Location): Float {
        return 0f  // Ganti dengan perhitungan jarak yang sesuai
    }
}