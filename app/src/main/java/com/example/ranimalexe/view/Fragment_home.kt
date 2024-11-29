package com.example.ranimalexe.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ranimalexe.R
import com.example.ranimalexe.service.TrackingService
import com.example.ranimalexe.storage.UserData
import com.example.ranimalexe.viewmodel.PetViewModel
import com.example.ranimalexe.viewmodel.RunningViewModel

class fragment_home : Fragment() {

    private lateinit var distanceTextView: TextView
    private lateinit var selectedShellImage: ImageView
    private lateinit var selectedHatImage: ImageView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var totalExpView: TextView
    private lateinit var totalScore: TextView



    private val petViewModel: PetViewModel by activityViewModels()
    private val runningViewModel: RunningViewModel by activityViewModels()  // Menggunakan RunningViewModel untuk jarak tracking

    // Menyimpan instance TrackingService
    private var trackingService: TrackingService? = null
    private var isTracking = false

    // Menyimpan binded service (untuk mengakses service)
    private val trackingServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TrackingService.LocalBinder
            trackingService = binder.getService()
            Log.d("FragmentHome", "TrackingService connected")

            // Pastikan observer di RunningViewModel dimulai
            trackingService?.let {
                // Panggil observeDistanceFromService hanya jika belum dipasang
                runningViewModel.observeTotalDistanceFromService(it)
                runningViewModel.observeDistanceFromService(it)
                Log.d("FragmentHome", "RunningViewModel is observing TrackingService distance")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            trackingService = null
            Log.d("FragmentHome", "TrackingService disconnected")
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Inisialisasi UI
        distanceTextView = rootView.findViewById(R.id.txtCurrentEXP)
        totalExpView = rootView.findViewById(R.id.txtTotalEXP)
        totalScore = rootView.findViewById(R.id.txtTotalScore)
        selectedShellImage = rootView.findViewById(R.id.selectedShellImage)
        selectedHatImage = rootView.findViewById(R.id.selectedHatImage)

        // Tombol-tombol untuk kontrol tracking service
        startButton = rootView.findViewById(R.id.btnStart)
        pauseButton = rootView.findViewById(R.id.btnPause)
        stopButton = rootView.findViewById(R.id.btnStop)

        // Setup Observers untuk PetViewModel (untuk customisasi)
        petViewModel.customization.observe(viewLifecycleOwner) { customization ->
            selectedShellImage.setImageResource(customization.clothes)
            selectedHatImage.setImageResource(customization.hat)
            Log.d("FragmentHome", "Customization updated: Clothes = ${customization.clothes}, Hat = ${customization.hat}")
        }

        // Setup Observers untuk RunningViewModel (untuk jarak)
        runningViewModel.distance.observe(viewLifecycleOwner) { distance ->
            Log.d("FragmentHome", "Jarak yang diterima: $distance meter")  // Debugging log jarak
            updateDistance(distance)
        }

        // Button actions
        startButton.setOnClickListener {
            Log.d("FragmentHome", "Start button clicked")
//            if (trackingService != null && !isTracking) {
            startTracking()
//            }
        }

        pauseButton.setOnClickListener {
            Log.d("FragmentHome", "Pause button clicked")
            if (trackingService != null && isTracking) {
                pauseTracking()
            }
        }

        stopButton.setOnClickListener {
            Log.d("FragmentHome", "Stop button clicked")
            if (trackingService != null) {
                stopTracking()
            }
        }

        return rootView
    }

    // Fungsi untuk memperbarui jarak di UI
    private fun updateDistance(distance: Float) {
        // Log untuk mengecek nilai jarak yang akan ditampilkan
        Log.d("FragmentHome", "Memperbarui jarak di UI: ${distance / 1000} km")  // Debugging log update UI
        // Update jarak yang ditampilkan di TextView
        distanceTextView.text = UserData.user.currentExp.toString()
        totalExpView.text = UserData.user.totalExp.toString()
        totalScore.text = UserData.user.totalScore.toString()
    }

    // Fungsi untuk memulai tracking
    // Fungsi untuk memulai tracking
    private fun startTracking() {
        // Pastikan servis terhubung
        val intent = Intent(context, TrackingService::class.java)
        requireContext().startService(intent)
        context?.bindService(intent, trackingServiceConnection, Context.BIND_AUTO_CREATE)

//        val serviceIntent = Intent(this, TrackingService::class.java)
//        startService(serviceIntent)
//        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        // Setelah servis terhubung, mulai lokasi tracking
        trackingService?.let {
            it.startLocationUpdates()  // Mulai pembaruan lokasi
            Log.d("FragmentHome", "Tracking Started")
            // Pastikan RunningViewModel mengamati perubahan jarak setelah tracking dimulai
            runningViewModel.observeDistanceFromService(it)
        }
        isTracking = true
    }

    // Fungsi untuk menghentikan tracking
    private fun stopTracking() {
        // Hentikan pembaruan lokasi
        trackingService?.stopLocationUpdates()

        // Unbind service agar tidak ada kebocoran memori
        context?.unbindService(trackingServiceConnection)

        // Set status tracking ke false untuk menandakan bahwa tracking telah berhenti
        isTracking = false
        Log.d("FragmentHome", "Tracking Stopped")

        // Jika Anda ingin menghentikan service sepenuhnya (tergantung pada kebutuhan)
        // Hentikan service jika dijalankan dengan startService()
        val intent = Intent(context, TrackingService::class.java)
        context?.stopService(intent)
    }

    // Fungsi untuk menjeda tracking
    private fun pauseTracking() {
        // Cukup berhentikan pembaruan lokasi, tapi jangan unbind servis
        trackingService?.stopLocationUpdates()
        isTracking = false
        Log.d("FragmentHome", "Tracking Paused")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentHome", "Fragment onDestroy, unbinding service...")
        // Unbind service jika fragment dihancurkan
        trackingService?.stopLocationUpdates()
        context?.unbindService(trackingServiceConnection)
    }
}
