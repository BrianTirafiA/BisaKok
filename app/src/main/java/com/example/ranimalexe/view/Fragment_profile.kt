package com.example.ranimalexe.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ranimalexe.R
import com.example.ranimalexe.viewmodel.ProfileViewModel

class fragment_profile : Fragment() {

    private lateinit var editProfile: Button
    private lateinit var username: TextView
    private lateinit var username2: TextView
    private lateinit var email: TextView
    private lateinit var email2: TextView
    private lateinit var totalExp: TextView
    private lateinit var totalScore: TextView
    private lateinit var age: TextView
    private lateinit var userId: TextView

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("FragmentProfile", "onCreateView: Inflating the layout")
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inisialisasi komponen UI
        Log.d("FragmentProfile", "onCreateView: Initializing UI components")
//        editProfile = view.findViewById(R.id.editProfile)
        username = view.findViewById(R.id.username)
        username2 = view.findViewById(R.id.lblUsername_Result)
        email = view.findViewById(R.id.email)
        email2 = view.findViewById(R.id.lblEmail_Result)
        totalExp = view.findViewById(R.id.lblTotalEXPResult)
        totalScore = view.findViewById(R.id.lblTotalScoreResult)
        age = view.findViewById(R.id.lblAge_Result)
        userId = view.findViewById(R.id.lblUserID_Result)

        // Menambahkan listener klik untuk tombol
        Log.d("FragmentProfile", "onCreateView: Setting click listener for editProfile button")
        editProfile.setOnClickListener {
            Log.d("FragmentProfile", "editProfile clicked")
        }

        // Menyediakan ViewModel
        Log.d("FragmentProfile", "onCreateView: Getting ViewModel instance")
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Pastikan profileViewModel sudah diinisialisasi
        Log.d("FragmentProfile", "profileViewModel initialized: $profileViewModel")

        // Mengambil data pengguna berdasarkan ID (misalnya ID 2)
        Log.d("FragmentProfile", "onCreateView: Requesting user data for userId = 2")
        profileViewModel.getUserById(6)

        // Mengamati perubahan data pengguna dari ViewModel
        Log.d("FragmentProfile", "onCreateView: Observing user data changes")
        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Log.d("FragmentProfile", "User data received: $user")

            user?.let {
                Log.d("FragmentProfile", "Binding data to UI")
                // Mengikat data ke UI hanya jika data tidak null
                username.text = it.username ?: "Username not available"
                username2.text = it.username ?: "Username not available"
                email.text = it.email ?: "Email not available"
                email2.text = it.email ?: "Email not available"
                totalExp.text = it.totalExp.toString()  // Mengikat totalExp ke TextView
                totalScore.text = it.totalScore.toString()  // Mengikat totalScore ke TextView
                age.text = it.age.toString()  // Mengikat age ke TextView
                userId.text = it.userId.toString()  // Mengikat userId ke TextView
                Log.d("FragmentProfile", "Data binding successful")
            } ?: run {
                // Jika data pengguna tidak ditemukan, beri tahu pengguna
                Log.e("FragmentProfile", "User not found")
                Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}
