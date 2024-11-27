package com.example.ranimalexe

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_up)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Mengatur padding system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menghubungkan UI ke variabel
        val usernameEditText = findViewById<EditText>(R.id.UsernameBox)
        val ageEditText = findViewById<EditText>(R.id.AgeBox)
        val emailEditText = findViewById<EditText>(R.id.EmailBox)
        val passwordEditText = findViewById<EditText>(R.id.PassBox)
        val signUpButton = findViewById<Button>(R.id.MakeAccButton)

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val age = ageEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validasi input
            if (username.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Daftarkan pengguna
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Arahkan ke halaman utama atau RunningActivity
                            val intent = Intent(this, RunningActivity::class.java)
                            startActivity(intent)
                            finish()  // Mengakhiri aktivitas SignUp
                        } else {
                            Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
