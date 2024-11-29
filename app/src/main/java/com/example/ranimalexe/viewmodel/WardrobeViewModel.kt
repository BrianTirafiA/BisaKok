package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.PetCustomization
import com.example.ranimalexe.model.Shell
import com.example.ranimalexe.storage.UserData

class HatViewModel : ViewModel() {
    private val _allHats = MutableLiveData<List<Hat>>()
    val allHats: LiveData<List<Hat>> get() = _allHats

    private val _filteredHat = MutableLiveData<List<Hat>>()
    val filteredHat: LiveData<List<Hat>> get() = _filteredHat

    init {
        loadCosmetic()
        filterHatById(4..24)
    }

    private fun loadCosmetic() {
        _allHats.value = UserData.hats
    }

    fun filterHatById(range: IntRange) {
        _filteredHat.value = _allHats.value?.filter { it.id in range }
    }
}

class ShellViewModel : ViewModel() {
    private val _allShells = MutableLiveData<List<Shell>>()
    val allShells: LiveData<List<Shell>> get() = _allShells

    private val _filteredShells = MutableLiveData<List<Shell>>()
    val filteredShells: LiveData<List<Shell>> get() = _filteredShells

    init {
        loadShells()
        filterShellsById(4..24)
    }

    private fun loadShells() {
        _allShells.value = UserData.shells
    }

    fun filterShellsById(range: IntRange) {
        _filteredShells.value = _allShells.value?.filter { it.id in range }
    }
}



class PetViewModel : ViewModel() {

    private val _customization = MutableLiveData(PetCustomization())
    val customization: LiveData<PetCustomization> get() = _customization

    fun updatePet(petHead: Int) {
        _customization.value = _customization.value?.copy(petHead = petHead)
    }

    fun updatePet2(petHand: Int) {
        _customization.value = _customization.value?.copy(petHand = petHand)
    }

    fun updateClothes(clothes: Int) {
        _customization.value = _customization.value?.copy(clothes = clothes)
    }

    fun updateHat(hat: Int) {
        _customization.value = _customization.value?.copy(hat = hat)
    }
}