package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.FoodItem
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.storage.UserData

class UsableFoodAdapter(
    private val shopItemList: List<FoodItem>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<UsableFoodAdapter.FoodItemViewHolder>() {
    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(ShopCustom: FoodItem) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = UserData.GetFoodAmount(ShopCustom.id).toString()
            itemView.setOnClickListener {
                UserData.TryBuyFood(ShopCustom.id)
                onClick(ShopCustom.imageResId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopcostum_item, parent, false)
        return FoodItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        holder.bind(shopItem)
    }

    override fun getItemCount(): Int = shopItemList.size
}