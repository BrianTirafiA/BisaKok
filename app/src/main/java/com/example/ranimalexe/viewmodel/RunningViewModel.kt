package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.service.TrackingService

class RunningViewModel : ViewModel() {

    // MutableLiveData untuk jarak, untuk dapat mengubah nilai
    private val _distance = MutableLiveData<Float>()

    // LiveData yang akan diobservasi oleh UI (Fragment/Activity)
    val distance: LiveData<Float> = _distance

    // Fungsi untuk memperbarui jarak
    fun updateDistance(newDistance: Float) {
        _distance.value = newDistance
    }

    // Fungsi untuk mengamati perubahan jarak dari TrackingService
    fun observeDistanceFromService(service: TrackingService) {
        service.getTotalDistanceLiveData().observeForever { newDistance ->
            _distance.value = newDistance
        }
    }
}

