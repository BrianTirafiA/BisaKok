package com.example.ranimalexe.view

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ranimalexe.R
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    // Inisialisasi FirebaseAuth
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

        val usernameEditText = findViewById<EditText>(R.id.UsernameBox)
        val ageEditText = findViewById<EditText>(R.id.AgeBox)
        val emailEditText = findViewById<EditText>(R.id.EmailBox)
        val passwordEditText = findViewById<EditText>(R.id.PassBox)

        val MakeAccButton = findViewById<Button>(R.id.MakeAccButton)
        MakeAccButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validasi input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Panggil method untuk mendaftar dengan email dan password
            signUpWithEmail(email, password)
        }
    }

    // Fungsi untuk signup menggunakan email dan password
    private fun signUpWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User berhasil terdaftar
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Signup successful", Toast.LENGTH_SHORT).show()

                    // Pindah ke halaman selanjutnya (RunningActivity)
                    val intent = Intent(this, RunningActivity::class.java)
                    startActivity(intent)
                    finish()  // Hapus activity signup dari stack
                } else {
                    // Jika signup gagal, tampilkan error
                    Toast.makeText(baseContext, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
