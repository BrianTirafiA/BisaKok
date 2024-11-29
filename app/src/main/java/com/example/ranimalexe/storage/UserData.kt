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
    var hats : MutableList<Hat> = mutableListOf(
        Hat(0, "Basic Red", R.drawable.default_hat1, "proof of your birth", true),
        Hat(1, "Basic Blue", R.drawable.default_hat2, "proof of your birth", true),
        Hat(2, "Basic Green", R.drawable.default_hat3, "proof of your birth", true),
        Hat(3, "Extinct", R.drawable.hdino, "Reborn from meteor", true),
        Hat(4, "Element 'R'", R.drawable.helred, "COMMON! True nature", true),
        Hat(5, "Element 'B'", R.drawable.helblue, "COMMON! True nature", true),
        Hat(6, "Element 'G'", R.drawable.helgreen, "COMMON! True nature", true),
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
    var shells : MutableList<Shell> = mutableListOf(
        Shell(0, "Basic Red", R.drawable.default_shell1, "proof of your birth", true),
        Shell(1, "Basic Blue", R.drawable.default_shell2, "proof of your birth", true),
        Shell(2, "Basic Green", R.drawable.default_shell3, "proof of your birth", true),
        Shell(3, "Extinct", R.drawable.sdino, "Reborn from meteor", true),
        Shell(4, "Element 'R'", R.drawable.selred, "COMMON! True nature", true),
        Shell(5, "Element 'B'", R.drawable.selblue, "COMMON! True nature", true),
        Shell(6, "Element 'G'", R.drawable.selgreen, "COMMON! True nature", true),
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