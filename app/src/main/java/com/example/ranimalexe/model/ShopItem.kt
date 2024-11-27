package com.example.ranimalexe.model

data class ShopItem(
    var id: String = "",
    var itemId : String = "",
    var price: Int = 0,
    var itemType: ItemType = ItemType.FoodItem,

    var name : String = "",
    var imageResId : Int = 0
)

enum class ItemType{
    WardrobeItem, FoodItem
}

