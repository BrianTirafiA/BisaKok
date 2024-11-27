package com.example.ranimalexe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.R
import com.example.ranimalexe.model.EventItem

class EventAdapter(private val eventItemList: List<EventItem>) : RecyclerView.Adapter<EventAdapter.EventItemViewHolder>() {
    inner class EventItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val desc: TextView = itemView.findViewById(R.id.desc)
        private val completion: TextView = itemView.findViewById(R.id.persen)
        val completedSection: View = itemView.findViewById(R.id.completedSection)
        val remainingSection: View = itemView.findViewById(R.id.remainingSection)

        fun bind(eventItem: EventItem) {
            itemName.text = eventItem.name
            desc.text = eventItem.desc
            completion.text = eventItem.completion.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        val eventItem = eventItemList[position]
        holder.bind(eventItem)
        val progressCompleted = eventItem.completion
        val progressRemaining = 100 - progressCompleted

        val completedParams = holder.completedSection.layoutParams as LinearLayout.LayoutParams
        completedParams.weight = progressCompleted.toFloat()
        holder.completedSection.layoutParams = completedParams

        val remainingParams = holder.remainingSection.layoutParams as LinearLayout.LayoutParams
        remainingParams.weight = progressRemaining.toFloat()
        holder.remainingSection.layoutParams = remainingParams

    }

    override fun getItemCount(): Int = eventItemList.size
}