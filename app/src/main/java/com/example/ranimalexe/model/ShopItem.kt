package com.example.ranimalexe.model

data class ShopItem(
    var id: String,
    var itemId : String,
    var price: Int,
    var itemType: ItemType,

    var name : String,
    var imageResId : Int
)

enum class ItemType{
    WardrobeItem, FoodItem
}

