package com.example.ranimalexe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.model.Users
import com.example.ranimalexe.service.FirestoreApi

class ProfileViewModel : ViewModel() {

    private val firestoreApi = FirestoreApi()

    // LiveData untuk pengguna
    private val _user = MutableLiveData<Users>()
    val user: LiveData<Users> get() = _user

    // Fungsi untuk mengambil data pengguna berdasarkan ID
//    fun getUserById(userId: Int) {
//        Log.d("ProfileViewModel", "Fetching user data for ID: $userId")
//
//        firestoreApi.getUsersById(userId)
//            .addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot != null) {
//                    Log.d("ProfileViewModel", "Document found: ${documentSnapshot.id}")
//                    val user = documentSnapshot.toObject(Users::class.java)
//                    if (user != null) {
//                        Log.d("ProfileViewModel", "User data: $user")
//                        _user.value = user // Mengupdate LiveData dengan data pengguna
//                    } else {
//                        Log.d("ProfileViewModel", "User data is null")
//                        _user.value = null
//                    }
//                } else {
//                    Log.d("ProfileViewModel", "No document found for userId: $userId")
//                    _user.value = null
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("ProfileViewModel", "Failed to fetch user data", exception)
//                _user.value = null
//            }
//    }

    fun getUserById(userId: Int) {
        // Data dummy untuk testing
        val dummyUser = Users(
            userId = 1,
            username = "john_doe",
            password = "password123",
            age = 30,
            email = "john.doe@example.com",
            totalExp = 500,
            totalScore = 1500
        )

        // Simulasi pemanggilan API yang berhasil
//        _user.value = dummyUser

        // Jika Anda ingin menambahkan simulasi kegagalan, gunakan kode berikut:
        // _user.value = null
    }
}
