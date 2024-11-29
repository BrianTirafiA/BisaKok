package com.example.ranimalexe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.service.TrackingService
import com.example.ranimalexe.storage.UserData

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
    fun observeTotalDistanceFromService(service: TrackingService) {
        service.getTotalDistanceLiveData().observeForever { newDistance ->
            _distance.value = newDistance
        }
    }

    fun observeDistanceFromService(service: TrackingService){
        service.getDistanceLiveData().observeForever{ distance ->
            Log.d("Running Test", "Deviation: $distance");
            UserData.ModifyExpBy(Math.round(distance))
            Log.d("Running Test", "Current EXP: ${UserData.user.currentExp}");
            UserData.ModifyScoreBy(Math.round(distance * UserData.user.health))
            Log.d("Running Test", "Total Score: ${UserData.user.totalScore}");
            _distance.value = distance
        }
    }
}

