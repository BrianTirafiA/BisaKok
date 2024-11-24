package com.example.ranimalexe.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ranimalexe.R

//import kotlinx.android.synthetic.main.bottom_navbar.*

//private val previousLocation: Location? = null
//private const val totalDistance = 0f

class RunningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.bottom_navbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Memuat fragment default
        if (savedInstanceState == null) {
            loadFragment(fragment_home())  // Ganti dengan fragment default yang ingin ditampilkan
        }

        val navProfile = findViewById<LinearLayout>(R.id.nav_profile)
        navProfile.setOnClickListener { loadFragment(fragment_profile()) }

        val navEvent = findViewById<LinearLayout>(R.id.nav_event)
        navEvent.setOnClickListener { loadFragment(fragment_event()) }

        val navShop = findViewById<LinearLayout>(R.id.nav_shop)
        navShop.setOnClickListener { loadFragment(fragment_shop()) }

        val navWardrobe = findViewById<LinearLayout>(R.id.nav_wardrobe)
        navWardrobe.setOnClickListener { loadFragment(fragment_wardrobe()) }

        val navHome = findViewById<LinearLayout>(R.id.nav_home)
        navHome.setOnClickListener { loadFragment(fragment_home()) }
    }

    private fun loadFragment(fragment: Fragment) {
        // Mengganti fragment yang ditampilkan
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main, fragment)
        transaction.addToBackStack(null)  // Menambahkan ke back stack untuk navigasi kembali
        transaction.commit()
    }
}
