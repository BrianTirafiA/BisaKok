package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.PetCustomization
import com.example.ranimalexe.model.Shell
class HatViewModel : ViewModel() {
    private val _hat = MutableLiveData<List<Hat>>()
    val hatList: LiveData<List<Hat>> get() = _hat

    init {
        loadCosmetic()
    }

    private fun loadCosmetic() {
        _hat.value = listOf(
            Hat(0, "Basic Red", R.drawable.default_hat1, "proof of your birth", false),
            Hat(1, "Meteor", R.drawable.meteorhead, "Coming for extinction", false),
            Hat(2, "Beret", R.drawable.beret, "Honourable soldier", true),
            Hat(3, "Meteor", R.drawable.meteorhead, "Coming for extinction", false),
            Hat(4, "Beret", R.drawable.beret, "Honourable soldier", true),
            Hat(5, "Meteor", R.drawable.meteorhead, "Coming for extinction", false),
            Hat(6, "Beret", R.drawable.beret, "Honourable soldier", true),
            Hat(7, "Meteor", R.drawable.meteorhead, "Coming for extinction", false),
            Hat(8, "Beret", R.drawable.beret, "Honourable soldier", true),
            Hat(9, "Meteor", R.drawable.meteorhead, "Coming for extinction", false),
            Hat(10, "Beret", R.drawable.beret, "Honourable soldier", true),
        )
    }
}

class ShellViewModel : ViewModel() {
    private val _shell = MutableLiveData<List<Shell>>()
    val shellList: LiveData<List<Shell>> get() = _shell

    init {
        loadCosmetic()
    }

    private fun loadCosmetic() {
        _shell.value = listOf(
            Shell(0, "Basic Red", R.drawable.default_shell1, "proof of your birth", false),
            Shell(1, "Cosmic", R.drawable.cometshell, "it radiates cosmic power", false),
            Shell(2, "Veteran", R.drawable.milishell, "Ghost of battlefield", true),
            Shell(3, "Cosmic", R.drawable.cometshell, "it radiates cosmic power", false),
            Shell(4, "Veteran", R.drawable.milishell, "Ghost of battlefield", true),
            Shell(5, "Cosmic", R.drawable.cometshell, "it radiates cosmic power", false),
            Shell(6, "Veteran", R.drawable.milishell, "Ghost of battlefield", true),
            Shell(7, "Cosmic", R.drawable.cometshell, "it radiates cosmic power", false),
            Shell(8, "Veteran", R.drawable.milishell, "Ghost of battlefield", true),
            Shell(9, "Cosmic", R.drawable.cometshell, "it radiates cosmic power", false),
            Shell(10, "Veteran", R.drawable.milishell, "Ghost of battlefield", true),
        )
    }
}


class PetViewModel : ViewModel() {

    private val _customization = MutableLiveData(PetCustomization())
    val customization: LiveData<PetCustomization> get() = _customization

    fun updatePet(pet: Int) {
        _customization.value = _customization.value?.copy(pet = pet)
    }

    fun updateClothes(clothes: Int) {
        _customization.value = _customization.value?.copy(clothes = clothes)
    }

    fun updateHat(hat: Int) {
        _customization.value = _customization.value?.copy(hat = hat)
    }
}