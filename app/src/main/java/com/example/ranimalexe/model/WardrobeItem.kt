package com.example.ranimalexe.model

data class WardrobeItem(
    val id: String,
    val name: String,
    val imageResId: Int,
    val desc: String,
    val slot: WardrobeSlot
)