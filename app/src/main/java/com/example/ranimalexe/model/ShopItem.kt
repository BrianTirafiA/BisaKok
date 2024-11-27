package com.example.ranimalexe.model

data class ShopItem(
    val id: String,
    val itemId : String,
    val price: Int,
    val itemType: ItemType
)

enum class ItemType{
    WardrobeItem, FoodItem
}

