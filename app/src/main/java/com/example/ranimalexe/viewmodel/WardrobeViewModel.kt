package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.WardrobeItem
import com.example.ranimalexe.model.WardrobeSlot

class WardrobeViewModel : ViewModel() {
    private val _cosmetic = MutableLiveData<List<WardrobeItem>>()
    val cosmeticList: LiveData<List<WardrobeItem>> get() = _cosmetic

    init {
        loadCosmetic()
    }

    private fun loadCosmetic() {
        _cosmetic.value = listOf(
            WardrobeItem("1", "MeteorHead", R.drawable.meteorhead, "Coming for extinction", WardrobeSlot.Hat),
            WardrobeItem("2", "Beret", R.drawable.beret, "Honourable soldier", WardrobeSlot.Hat),
            WardrobeItem("3", "Cosmic Shell", R.drawable.cometshell, "it radiates cosmic power", WardrobeSlot.Shell),
            WardrobeItem("4", "Veteran Shell", R.drawable.milishell, "Ghost of battlefield", WardrobeSlot.Shell),
        )
    }
}