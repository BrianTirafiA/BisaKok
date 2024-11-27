package com.example.ranimalexe.api

import android.util.Log
import com.example.ranimalexe.model.ShopItem
import com.example.ranimalexe.model.WardrobeItem
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.*

class ItemAPI {

    suspend fun GetShopItems() : MutableList<ShopItem> {
        var success : Boolean = false
        var faulted : Boolean = false

        var list : MutableList<ShopItem> = mutableListOf()

        FirestoreAPI().getDocuments(
            "ShopItems",
            { qs ->
                for (doc in qs){
                    var item = doc.toObject<ShopItem>()
                    item.id = doc.id
                    list.add(item)
                }
                success = true
            },
            { e ->
                faulted = true
                Log.e(e.message, e.toString())
            }
        )

        while (!(success || faulted)){
            delay(100)
        }

        return list
    }
}