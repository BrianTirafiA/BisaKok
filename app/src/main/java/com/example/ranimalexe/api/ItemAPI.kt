package com.example.ranimalexe.api

import android.util.Log
import com.example.ranimalexe.model.ShopItem
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.*

class ItemAPI {

    suspend fun getShopItems() : MutableList<ShopItem> {
        var success : Boolean = false
        var faulted : Boolean = false

        var list : MutableList<ShopItem> = mutableListOf()

        Log.d("Test Log", "Firebase starting")

        FirestoreAPI().getDocuments(
            "ShopItems",
            { qs ->
                Log.d("Test Log", "Parsing")
                if (qs.isEmpty){
                    Log.e("Test Log", "Empty!")
                }
                for (doc in qs){
                    Log.d("Test Log", doc.id)
                    try {
                        var item = doc.toObject<ShopItem>()
                        Log.d("Test Log", item.toString())
                    }
                    catch (e : Exception) {
                        Log.e("Test Log", e.message.toString())
                    }
                    //item.id = doc.id
                    //list.add(item)
                }
                success = true
            },
            { e ->
                faulted = true
                Log.e("Test Log", e.message.toString())
            }
        )

        while (!(success || faulted)){
            Log.d("Test Log", "Waiting...")
            delay(100)
        }

        Log.d("Test Log", "Escaped")
        Log.d("Test Log", list.count().toString())

        return list
    }
}