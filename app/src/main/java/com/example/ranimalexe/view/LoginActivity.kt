package com.example.ranimalexe.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ranimalexe.R
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.content.SharedPreferences


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailBox = findViewById<EditText>(R.id.EmailBox)
        val passBox = findViewById<EditText>(R.id.PassBox)
        val loginButton = findViewById<Button>(R.id.LoginButton)

        loginButton.setOnClickListener {
            val email = emailBox.text.toString()
            val password = passBox.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginUser(email, password)
            }
        }



        fun saveUserSession(context: Context) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                val userId = currentUser.uid
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("user_id", userId) // Menyimpan uid
                editor.apply()
            }
        }

    }
    fun loginUser(email: String, password: String) {
//         Authenticate the user with Firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful
                    val user = auth.currentUser
                    val intent = Intent(this, RunningActivity::class.java)
                    startActivity(intent)
                    finish()  // Close LoginActivity
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show()
                }
            }
    }
}