package com.example.ranimalexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ranimalexe.R
import com.example.ranimalexe.view.adapter.HatAdapter
import com.example.ranimalexe.view.adapter.ShellAdapter
import com.example.ranimalexe.viewmodel.HatViewModel
import com.example.ranimalexe.viewmodel.PetViewModel
import com.example.ranimalexe.viewmodel.ShellViewModel

class fragment_home : Fragment() {

    private lateinit var distanceTextView: TextView
    private lateinit var selectedShellImage: ImageView
    private lateinit var selectedHatImage: ImageView
    private val petViewModel: PetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
//        distanceTextView = rootView.findViewById(R.id.TodayExpCount)  // pastikan ada TextView untuk jarak

        selectedShellImage = rootView.findViewById(R.id.selectedShellImage)
        selectedHatImage = rootView.findViewById(R.id.selectedHatImage)

        // Observe changes in the PetViewModel for customization updates
        petViewModel.customization.observe(viewLifecycleOwner) { customization ->
            selectedShellImage.setImageResource(customization.clothes)
            selectedHatImage.setImageResource(customization.hat)
        }
        return rootView
    }

    // Fungsi untuk memperbarui jarak di UI
    fun updateDistance(distance: Float) {
        // Update jarak yang ditampilkan
        distanceTextView.text = "${distance}"
    }
}

