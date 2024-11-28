package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.PetCustomization
import com.example.ranimalexe.model.Shell
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
        _allHats.value = listOf(
            Hat(0, "Basic Red", R.drawable.default_hat1, "proof of your birth", true),
            Hat(1, "Basic Blue", R.drawable.default_hat2, "proof of your birth", true),
            Hat(2, "Basic Green", R.drawable.default_hat3, "proof of your birth", true),
            Hat(3, "Extinct", R.drawable.hdino, "Reborn from meteor", false),
            Hat(4, "Element 'R'", R.drawable.helred, "COMMON! True nature", true),
            Hat(5, "Element 'B'", R.drawable.helblue, "COMMON! True nature", false),
            Hat(6, "Element 'G'", R.drawable.helgreen, "COMMON! True nature", false),
            Hat(7, "Batik", R.drawable.htikred, "COMMON! 100% Original", false),
            Hat(8, "Batik", R.drawable.htikblue, "COMMON! 100% Original", false),
            Hat(9, "Batik", R.drawable.htikgreen, "COMMON! 100% Original", false),
            Hat(10, "Stone", R.drawable.hstone, "COMMON! Nature texture", false),
            Hat(11, "Metal", R.drawable.hmetal, "COMMON! Nature texture", false),
            Hat(12, "Steel", R.drawable.hsteel, "COMMON! Nature texture", false),
            Hat(13, "BirchWood", R.drawable.hbirch, "COMMON! Nature texture", false),
            Hat(14, "OakWood", R.drawable.hoak, "COMMON! Nature texture", false),
            Hat(15, "DarkWood", R.drawable.hdarkoak, "COMMON! Nature texture", false),
            Hat(16, "'R'0.2", R.drawable.helmagma, "UNCOMMON! Improved nature formula", false),
            Hat(17, "'B'0.2", R.drawable.helice, "UNCOMMON! Improved nature formula", false, 200),
            Hat(18, "'G'0.2", R.drawable.huelgreen, "UNCOMMON! Improved nature formula", false, 200),
            Hat(19, "Flaming Hot", R.drawable.hflame, "UNCOMMON! Burn everything", false, 200),
            Hat(20, "Cold Breeze", R.drawable.hnwater, "UNCOMMON! Felt like winter", false, 200),
            Hat(21, "Toxic Green", R.drawable.htoxic, "UNCOMMON! Pure Acid", false, 200),
            Hat(22, "Melting Red", R.drawable.hfirered, "RARE!! Straight from the core", false, 600),
            Hat(23, "Electric Ocean", R.drawable.hewater, "RARE!! Don't jump into it", false, 600),
            Hat(24, "Death Touch", R.drawable.hskull, "RARE!! A touch and it's over", false, 600),
            Hat(26, "Ruby", R.drawable.hruby, "RARE!! Original red, made with love", false),
            Hat(27, "Azure", R.drawable.hazure, "RARE!! Original blue, made with love", false),
            Hat(28, "Jade", R.drawable.hjade, "RARE!! Original green, made with love", false),
            Hat(29, "Galaxy", R.drawable.hgalaxy, "LEGENDARY!!! Never ending space", false),
            Hat(30, "Uranium", R.drawable.huranium, "LEGENDARY!!! Eradicate all life", false),
            Hat(31, "Zeus", R.drawable.hzeus, "LEGENDARY!!! Struck like a lightning", false),
        )
    }

    fun filterHatById(range: IntRange) {
        _filteredHat.value = _allHats.value?.filter { it.id in range && !it.status }
    }

    fun unlockHat(hatId: Int) {
        _allHats.value = _allHats.value?.map {
            if (it.id == hatId) it.copy(status = true) else it
        }
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
        _allShells.value = listOf(
            Shell(0, "Basic Red", R.drawable.default_shell1, "proof of your birth", true),
            Shell(1, "Basic Blue", R.drawable.default_shell2, "proof of your birth", true),
            Shell(2, "Basic Green", R.drawable.default_shell3, "proof of your birth", true),
            Shell(3, "Extinct", R.drawable.sdino, "Reborn from meteor", true),
            Shell(4, "Element 'R'", R.drawable.selred, "COMMON! True nature", false),
            Shell(5, "Element 'B'", R.drawable.selblue, "COMMON! True nature", false),
            Shell(6, "Element 'G'", R.drawable.selgreen, "COMMON! True nature", false),
            Shell(7, "Batik", R.drawable.stikred, "COMMON! 100% Original", false),
            Shell(8, "Batik", R.drawable.stikblue, "COMMON! 100% Original", false),
            Shell(9, "Batik", R.drawable.stikgreen, "COMMON! 100% Original", false),
            Shell(10, "Stone", R.drawable.sstone, "COMMON! Nature texture", false),
            Shell(11, "Metal", R.drawable.smetal, "COMMON! Nature texture", false),
            Shell(12, "Steel", R.drawable.ssteel, "COMMON! Nature texture", false),
            Shell(13, "BirchWood", R.drawable.sbirch, "COMMON! Nature texture", false),
            Shell(14, "OakWood", R.drawable.soak, "COMMON! Nature texture", false),
            Shell(15, "DarkWood", R.drawable.sdarkoak, "COMMON! Nature texture", false),
            Shell(16, "'R'0.2", R.drawable.selmagma, "UNCOMMON! Improved nature formula", false, 200),
            Shell(17, "'B'0.2", R.drawable.selice, "UNCOMMON! Improved nature formula", false, 200),
            Shell(18, "'G'0.2", R.drawable.suelgreen, "UNCOMMON! Improved nature formula", false, 200),
            Shell(19, "Flaming Hot", R.drawable.sflame, "UNCOMMON! Burn everything", false, 200),
            Shell(20, "Cold Breeze", R.drawable.snwater, "UNCOMMON! Felt like winter", false, 200),
            Shell(21, "Toxic Green", R.drawable.stoxic, "UNCOMMON! Pure Acid", false, 200),
            Shell(22, "Melting Red", R.drawable.sfirered, "RARE!! Straight from the core", false, 600),
            Shell(23, "Electric Ocean", R.drawable.sewater, "RARE!! Don't jump into it", false, 600),
            Shell(24, "Death Touch", R.drawable.sskull, "RARE!! A touch and it's over", false, 600),
            Shell(26, "Ruby", R.drawable.sruby, "RARE!! Original red, made with love", false),
            Shell(27, "Azure", R.drawable.sazure, "RARE!! Original blue, made with love", false),
            Shell(28, "Jade", R.drawable.sjade, "RARE!! Original green, made with love", false),
            Shell(29, "Galaxy", R.drawable.sgalaxy, "LEGENDARY!!! Never ending space", false),
            Shell(30, "Uranium", R.drawable.suranium, "LEGENDARY!!! Eradicate all life", false),
            Shell(31, "Zeus", R.drawable.szeus, "LEGENDARY!!! Struck like a lightning", false),
        )
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