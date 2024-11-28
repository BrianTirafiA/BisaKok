package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.Shell
import com.example.ranimalexe.model.ShopItem


class ShopAdapter(
    private val shopItemList: List<ShopItem>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShopAdapter.ShopItemViewHolder>() {
    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(ShopCustom: ShopItem) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = ShopCustom.price.toString()
            itemView.setOnClickListener { onClick(ShopCustom.imageResId) }
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
    private val onHatSelected: (Hat) -> Unit, // Handle selection
    private val onUnlockHat: (Hat) -> Unit
) : RecyclerView.Adapter<ShopHatAdapter.HatItemViewHolder>() {
    inner class HatItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(ShopCustom: Hat) {
            itemName.text = ShopCustom.name
            itemImage.setImageResource(ShopCustom.imageResId)
            itemPrice.text = ShopCustom.price.toString()
            itemView.setOnClickListener {
                if (!ShopCustom.status) {
                    onUnlockHat(ShopCustom) // Call the method to unlock the hat
                }
                onHatSelected(ShopCustom)
                hatItemList[position].status = true
                notifyItemChanged(position)
            }
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