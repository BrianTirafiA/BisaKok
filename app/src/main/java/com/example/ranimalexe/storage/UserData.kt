package com.example.ranimalexe.storage

import com.example.ranimalexe.model.*

object UserData {
    var user : Users = Users();
    var pet : Pet = Pet();
    var foodInventory : MutableList<FoodInventoryItem> = mutableListOf();
    var wardrobeInventory : MutableList<WardrobeinventoryItem> = mutableListOf();
    var missionCompletions : MutableList<MissionCompletion> = mutableListOf();

    //====================================== Currency ======================================//

    private fun TrySpendExp(amount : Int) : Boolean {
        if (amount <= user.currentExp){
            user.currentExp -= amount
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
        user.health += amount;
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
        var food : FoodItem = FoodItem() //TODO: get food from room db
        if (TrySpendExp(food.price)){
            IncrementFoodItemAmount(foodId);
            return true;
        }
        return false;
    }

    fun TryEatFood(foodId : Int) : Boolean {
        var food : FoodItem = FoodItem() //TODO: get food from room db
        for (item in foodInventory){
            if (item.foodId == foodId){
                item.amount--;
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
}