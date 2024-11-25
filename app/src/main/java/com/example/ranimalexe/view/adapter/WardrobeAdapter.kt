package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.WardrobeItem

class WardrobeAdapter(private val wardrobeItemList: List<WardrobeItem>) : RecyclerView.Adapter<WardrobeAdapter.WardrobeItemViewHolder>() {
    inner class WardrobeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val desc: TextView = itemView.findViewById(R.id.Desc)

        fun bind(wardrobeItem: WardrobeItem) {
            itemName.text = wardrobeItem.name
            desc.text = wardrobeItem.desc
            itemImage.setImageResource(wardrobeItem.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardrobeItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wardrobe_item, parent, false)
        return WardrobeItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WardrobeItemViewHolder, position: Int) {
        val wardrobeItem = wardrobeItemList[position]
        holder.bind(wardrobeItem)
    }

    override fun getItemCount(): Int = wardrobeItemList.size
}