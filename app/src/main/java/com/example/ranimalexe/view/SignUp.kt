package com.example.ranimalexe.view

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Users
import com.example.ranimalexe.model.Pet
import com.google.firebase.auth.FirebaseAuth
import com.example.ranimalexe.service.FirestoreApi
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query

class SignUp : AppCompatActivity() {
    // Inisialisasi FirebaseAuth

    private lateinit var firestoreApi: FirestoreApi
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi Firebase Auth
        auth = FirebaseAuth.getInstance()
        firestoreApi = FirestoreApi()

        val usernameEditText = findViewById<EditText>(R.id.UsernameBox)
        val ageEditText = findViewById<EditText>(R.id.AgeBox)
        val emailEditText = findViewById<EditText>(R.id.EmailBox)
        val passwordEditText = findViewById<EditText>(R.id.PassBox)
        val petNameEditText = findViewById<EditText>(R.id.petNameBox)

        val MakeAccButton = findViewById<Button>(R.id.MakeAccButton)

        MakeAccButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val username = usernameEditText.text.toString()
            val petName = petNameEditText.text.toString()
            val age = ageEditText.text.toString()

//            // Panggil method untuk mendaftar dengan email dan password
            signUpWithEmail(email, password, username, petName, age)
        }
    }

    // Fungsi untuk signup menggunakan email dan password
    private fun signUpWithEmail(
        email: String,
        password: String,
        username: String,
        petName: String,
        age: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User berhasil terdaftar
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Signup successful", Toast.LENGTH_SHORT).show()

                    // Pastikan UID sudah ada setelah pengguna terdaftar
                    val uid = user?.uid

                    // Membuat objek newUser dengan UID yang baru dibuat
                    val newUser = Users(
                        userId = 0, // userId bisa diotomatisasi atau dikelola sesuai kebutuhan
                        username = username,
                        password = password, // Jangan lupa enkripsi password sebelum menyimpannya!
                        age = age.toInt(),
                        email = email,
                        totalExp = 0,
                        totalScore = 0,
                        currentExp = 0
                    )

                    val newPet = Pet(
                        id = 0,  // ID pet bisa disesuaikan atau diotomatisasi
                        name = petName,
                        health = 100
                    )

                    // Simpan data pengguna ke Firestore dengan UID sebagai nama document

                    firestoreApi.writeUser(newUser)
                        .addOnSuccessListener {
                            // Menyimpan data pet setelah user berhasil disimpan
                            val newPet = Pet(
                                id = 0,
                                name = petName,
                                health = 100
                            )

                            firestoreApi.writePet(newPet)
                                .addOnSuccessListener {
                                    // Jika sukses, pindah ke RunningActivity
                                    val intent = Intent(this, RunningActivity::class.java)
                                    startActivity(intent)
                                    finish()  // Hapus activity signup dari stack
                                }
                                .addOnFailureListener { exception ->
                                    // Jika gagal simpan pet
                                    Toast.makeText(this, "Sign Up Pet Failed", Toast.LENGTH_SHORT)
                                        .show()
                                    Log.e("Signup", "Error saving pet data: ${exception.message}")
                                }
                        }
                        .addOnFailureListener { exception ->
                            // Jika gagal simpan user
                            Toast.makeText(this, "Sign Up User Failed", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("Signup", "Error saving user data: ${exception.message}")
                        }

                } else {
                    // Jika signup gagal, tampilkan error
                    Toast.makeText(
                        baseContext,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}
