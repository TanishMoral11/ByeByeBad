package com.example.byebyebad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MistakeAdapter(private val mistakes: List<Mistake>, private val dao: MistakeDao) :
    RecyclerView.Adapter<MistakeAdapter.MistakeViewHolder>() {

    inner class MistakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.text_title)
        val description: TextView = itemView.findViewById(R.id.text_description)
        val count: TextView = itemView.findViewById(R.id.text_count)
        val incrementButton: Button = itemView.findViewById(R.id.button_increment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MistakeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mistake, parent, false)
        return MistakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MistakeViewHolder, position: Int) {
        val mistake = mistakes[position]
        holder.title.text = mistake.title
        holder.description.text = mistake.description
        holder.count.text = "Count: ${mistake.count}"

        holder.incrementButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                dao.incrementCount(mistake.id)
                holder.count.text = "Count: ${mistake.count + 1}"
            }
        }
    }

    override fun getItemCount(): Int = mistakes.size
}
