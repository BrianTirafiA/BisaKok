package com.example.ranimalexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ranimalexe.R

class Fragment_editProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_edit_profile.xml
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
}
