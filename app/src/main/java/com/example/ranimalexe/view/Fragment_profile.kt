package com.example.ranimalexe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R

class fragment_profile : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(Constants.ARG_PARAM1)
            param2 = it.getString(Constants.ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi komponen UI di sini
        editProfile = view.findViewById(R.id.editProfile)

        // Menambahkan listener klik untuk tombol
        editProfile.setOnClickListener {
            // Navigasi ke fragment lain ketika tombol diklik
            navigateToEditProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun navigateToEditProfileFragment() {
        // Membuat fragment baru untuk halaman edit profile
        val editProfileFragment = Fragment_editProfile()  // Ganti dengan fragment yang sesuai

        // Menggunakan FragmentTransaction untuk mengganti fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, editProfileFragment)  // Ganti R.id.main dengan ID container sesuai layout
            .addToBackStack(null)  // Menambahkan fragment ke back stack agar bisa kembali
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_profile().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_PARAM1, param1)
                    putString(Constants.ARG_PARAM2, param2)
                }
            }
    }
}
