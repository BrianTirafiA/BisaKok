package com.example.ranimalexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ranimalexe.R

class fragment_home : Fragment() {

    private lateinit var distanceTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
//        distanceTextView = rootView.findViewById(R.id.TodayExpCount)  // pastikan ada TextView untuk jarak
        return rootView
    }

    // Fungsi untuk memperbarui jarak di UI
    fun updateDistance(distance: Float) {
        // Update jarak yang ditampilkan
        distanceTextView.text = "${distance}"
    }
}

