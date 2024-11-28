package com.example.ranimalexe.storage

import android.util.Log
import com.example.ranimalexe.R
import com.example.ranimalexe.model.*

object UserData {
    var user : Users = Users();
    var pet : Pet = Pet();
    var foodInventory : MutableList<FoodInventoryItem> = mutableListOf();
    var wardrobeInventory : MutableList<WardrobeinventoryItem> = mutableListOf();
    var missionCompletions : MutableList<MissionCompletion> = mutableListOf();

    //====================================== Temporary ======================================//
    var foodItems : MutableList<FoodItem> = mutableListOf(
        FoodItem(0, "Apel", 10, 100, R.drawable.borgir),
        FoodItem(1, "Nanas", 20, 150, R.drawable.borgir),
        FoodItem(2, "Melon", 30, 250, R.drawable.borgir)
    )
    //var wardrobeItems : MutableList<WardrobeItem>
    //=======================================================================================//



    //====================================== Currency ======================================//

    private fun TrySpendExp(amount : Int) : Boolean {
        if (amount <= user.currentExp){
            user.currentExp -= amount
            ModifyExpBy(-amount)
            return true;
        }
        else return false;
    }

    fun ModifyExpBy(amount : Int){
        user.currentExp += amount
        user.totalExp += amount
    }

    fun ModifyScoreBy(amount : Int){
        user.totalScore += amount;
    }

    private fun ModifyUserHealth(amount : Float){
        user.health = amount.coerceAtMost(100f).coerceAtLeast(0f)
        Log.d("User Data Log", "Health: " + user.health);
    }

    //====================================== User ======================================//

    fun UpdateProfile(username : String? = null, password : String?, age : Int?, email : String?){
        if (username != null){
            user.username = username;
        }
        if (password != null){
            user.password = password;
        }
        if (age != null){
            user.age = age;
        }
        if (email != null){
            user.email = email;
        }
    }

    //====================================== Food ======================================//

    private fun ModifyFoodItemAmount(foodId : Int, amount : Int){
        var item = foodInventory.find { it.foodId == foodId }

        if (item == null){
            var newItem = FoodInventoryItem(0, 0, foodId, 0);
            foodInventory.add(newItem);
            item = newItem;
        }

        item.amount = amount
    }

    private fun IncrementFoodItemAmount(foodId : Int){
        var item = foodInventory.find { it.foodId == foodId }

        if (item == null){
            var newItem = FoodInventoryItem(0, 0, foodId, 0);
            foodInventory.add(newItem);
            item = newItem;
        }

        item.amount++;
    }

    private fun DecrementFoodItemAmount(foodId : Int){
        var item = foodInventory.find { it.foodId == foodId }

        if (item == null){
            var newItem = FoodInventoryItem(0, 0, foodId, 0);
            foodInventory.add(newItem);
            item = newItem;
        }

        item.amount--;
    }

    fun TryBuyFood(foodId: Int) : Boolean {

        var food : FoodItem? = foodItems.find { foodId == it.id }

        Log.d("User Data Log", "Buying $foodId priced ${food?.price} with EXP: ${user.currentExp}");

        if (food == null){
            Log.d("User Data Log", "Buying $foodId null");
            return false;
        }

        if (TrySpendExp(food.price)){
            IncrementFoodItemAmount(foodId);
            Log.d("User Data Log", "Buying $foodId success");
            return true;
        }
        Log.d("User Data Log", "Buying $foodId failed");
        return false;
    }

    fun TryEatFood(foodId : Int) : Boolean {
        var food : FoodItem? = foodItems.find { foodId == it.id }

        if (food == null){
            return false;
        }

        for (invItem in foodInventory){
            if (invItem.foodId == foodId){
                DecrementFoodItemAmount(invItem.foodId)
                user.health += food.satiety
                return true;
            }
        }
        return false;
    }

    //====================================== Wardrobe ======================================//

    private fun AddWardrobeToInventory(wardrobeId : Int, equipNow : Boolean = false){
        if (!wardrobeInventory.any{ it.wardrobeId == wardrobeId }){
            var newItem = WardrobeinventoryItem(0, 0, wardrobeId, equipNow);
            wardrobeInventory.add(newItem);
        }
    }

    fun TryEquipWardrobe(wardrobeId : Int) : Boolean {
        for (item in wardrobeInventory){
            if (item.wardrobeId == wardrobeId){
                item.equipStatus = true;
                return true;
            }
        }
        return false;
    }

    //====================================== Mission ======================================//

    /*
    fun ModifyMissionCompletionPointBy(missionId : Int, amount : Int){
        var mission : Mission; //TODO: query mission from room db
        mission.pointToComplete
    }
    */
}