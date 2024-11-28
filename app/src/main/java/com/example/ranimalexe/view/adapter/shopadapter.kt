package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.FoodItem
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.Shell
import com.example.ranimalexe.storage.UserData


class ShopAdapter(
    private val shopItemList: List<FoodItem>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShopAdapter.ShopItemViewHolder>() {
    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(ShopCustom: FoodItem) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = ShopCustom.price.toString()
            itemView.setOnClickListener {
                UserData.TryBuyFood(ShopCustom.id)
                onClick(ShopCustom.imageResId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopcostum_item, parent, false)
        return ShopItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        holder.bind(shopItem)
    }

    override fun getItemCount(): Int = shopItemList.size
}

class ShopHatAdapter(
    private val hatItemList: List<Hat>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShopHatAdapter.HatItemViewHolder>() {
    inner class HatItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(ShopCustom: Hat) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = ShopCustom.price.toString()
            itemView.setOnClickListener { onClick(ShopCustom.imageResId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HatItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopcostum_item, parent, false)
        return HatItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HatItemViewHolder, position: Int) {
        val wardrobeItem = hatItemList[position]
        holder.bind(wardrobeItem)
    }

    override fun getItemCount(): Int = hatItemList.size
}

class ShopShellAdapter(
    private val shellItemList: List<Shell>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShopShellAdapter.ShellItemViewHolder>() {
    inner class ShellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(ShopCustom: Shell) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = ShopCustom.price.toString()
            itemView.setOnClickListener { onClick(ShopCustom.imageResId) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShellItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopcostum_item, parent, false)
        return ShellItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShellItemViewHolder, position: Int) {
        val shellItem = shellItemList[position]
        holder.bind(shellItem)
    }

    override fun getItemCount(): Int = shellItemList.size
}