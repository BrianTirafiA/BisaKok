package com.example.ranimalexe.model

import com.example.ranimalexe.R

data class Hat(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val desc: String,
    var status: Boolean,
    val price: Int= 100
)

data class Shell(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val desc: String,
    var status: Boolean,
    val price: Int= 100
)

data class PetCustomization(
    val petHead: Int = R.drawable.pet_kucing_head,
    val petHand: Int = R.drawable.pet_kucing_hand,
    val clothes: Int = R.drawable.default_shell1, // Default clothes
    val hat: Int = R.drawable.default_hat1      // Default hat
)