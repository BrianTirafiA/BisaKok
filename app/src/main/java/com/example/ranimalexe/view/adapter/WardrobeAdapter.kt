package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.Hat
import com.example.ranimalexe.model.Shell

class HatAdapter(
    private val hatItemList: List<Hat>,
    private val onClick: (Int) -> Unit
    ) : RecyclerView.Adapter<HatAdapter.HatItemViewHolder>() {
    inner class HatItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(wardrobeItem: Hat) {
            itemName.text = wardrobeItem.name
            itemImage.setImageResource(wardrobeItem.imageResId)
            itemView.setOnClickListener { onClick(wardrobeItem.imageResId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HatItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wardrobe_item, parent, false)
        return HatItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HatItemViewHolder, position: Int) {
        val wardrobeItem = hatItemList[position]
        holder.bind(wardrobeItem)
    }

    override fun getItemCount(): Int = hatItemList.size
}

class ShellAdapter(
    private val shellItemList: List<Shell>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShellAdapter.ShellItemViewHolder>() {
    inner class ShellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(wardrobeItem: Shell) {
            itemName.text = wardrobeItem.name
            itemImage.setImageResource(wardrobeItem.imageResId)
            itemView.setOnClickListener { onClick(wardrobeItem.imageResId) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShellItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wardrobe_item, parent, false)
        return ShellItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShellItemViewHolder, position: Int) {
        val shellItem = shellItemList[position]
        holder.bind(shellItem)
    }

    override fun getItemCount(): Int = shellItemList.size
}

