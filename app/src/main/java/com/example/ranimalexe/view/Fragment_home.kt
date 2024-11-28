package com.example.ranimalexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ranimalexe.R
import com.example.ranimalexe.service.FirestoreApi
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class fragment_home : Fragment() {

    private lateinit var distanceTextView: TextView
    private lateinit var username: TextView
    private lateinit var currentExp: TextView
    private lateinit var totalExp: TextView
    private lateinit var totalScore: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button

    private lateinit var amountMouse: TextView
    private lateinit var amountFish: TextView
    private lateinit var amountChick: TextView
    private lateinit var amountFruit: TextView
    private lateinit var amountRice: TextView

    private lateinit var healthPet: Number

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

