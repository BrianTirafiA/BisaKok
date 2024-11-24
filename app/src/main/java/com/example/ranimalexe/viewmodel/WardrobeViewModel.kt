package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.WardrobeItem

class WardrobeViewModel : ViewModel() {
    private val _cosmetic = MutableLiveData<List<WardrobeItem>>()
    val cosmeticList: LiveData<List<WardrobeItem>> get() = _cosmetic

    init {
        loadCosmetic()
    }

    private fun loadCosmetic() {
        _cosmetic.value = listOf(
            WardrobeItem(1, "Hat", R.drawable.grape, "its a hat", false),
            WardrobeItem(2, "Clothes", R.drawable.apple, "its a clothes", true),
            WardrobeItem(3, "Hat", R.drawable.grape, "its a hat", false),
            WardrobeItem(4, "Clothes", R.drawable.apple, "its a clothes", true),
        )
    }
}