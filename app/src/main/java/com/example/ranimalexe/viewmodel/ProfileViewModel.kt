package com.example.ranimalexe.viewmodel

import android.content.Context
import android.content.SharedPreferences
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
    fun getUserById(userUid: String) {
        Log.d("ProfileViewModel", "Fetching user data for ID: $userUid")

        firestoreApi.getUsersById(userUid)
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    Log.d("ProfileViewModel", "Document found: ${documentSnapshot.id}")
                    val user = documentSnapshot.toObject(Users::class.java)
                    if (user != null) {
                        Log.d("ProfileViewModel", "User data: $user")
                        _user.value = user!! // Mengupdate LiveData dengan data pengguna
                    } else {
                        Log.d("ProfileViewModel", "User data is null")
                        _user.value = Users()
                    }
                } else {
                    Log.d("ProfileViewModel", "No document found for userId: $userUid")
                    _user.value = Users()
                }
            }
            .addOnFailureListener { exception ->
                Log.e("ProfileViewModel", "Failed to fetch user data", exception)
                _user.value = Users()
            }
    }

//    fun getUserById(userId: Int) {
//        // Data dummy untuk testing
//        val dummyUser = Users(
//            userId = 1,
//            username = "john_doe",
//            password = "password123",
//            age = 30,
//            email = "john.doe@example.com",
//            totalExp = 500,
//            totalScore = 1500
//        )
//        _user.value = dummyUser
//    }
    // Simulasi pemanggilan API yang berhasil


    // Jika Anda ingin menambahkan simulasi kegagalan, gunakan kode berikut:
//     _user.value = null
}

