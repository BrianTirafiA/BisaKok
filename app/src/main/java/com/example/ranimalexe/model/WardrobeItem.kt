package com.example.ranimalexe.model

data class WardrobeItem(
    var id: String,
    val name: String,
    val imageResId: Int,
    val desc: String,
    val slot: WardrobeSlot
)