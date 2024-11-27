package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.ShopItem

class ShopAdapter(private val shopItemList: List<ShopItem>) : RecyclerView.Adapter<ShopAdapter.ShopItemViewHolder>() {
    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val Price: TextView = itemView.findViewById(R.id.Price)
        private val buyButton: Button = itemView.findViewById(R.id.buyButton)

        fun bind(shopItem: ShopItem) {
            itemName.text = shopItem.name
            Price.text = shopItem.price.toString()
            itemImage.setImageResource(shopItem.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ShopItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        holder.bind(shopItem)
    }

    override fun getItemCount(): Int = shopItemList.size
}