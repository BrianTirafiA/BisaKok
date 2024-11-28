package com.example.ranimalexe.model

import com.example.ranimalexe.R

data class Hat(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val desc: String,
    val status: Boolean
)

data class Shell(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val desc: String,
    val status: Boolean
)

data class PetCustomization(
    val pet: Int = R.drawable.default_shell1,
    val clothes: Int = R.drawable.default_shell1, // Default clothes
    val hat: Int = R.drawable.default_hat1      // Default hat
)