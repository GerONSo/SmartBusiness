package dev.geronso.smartbusiness

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.tags_card.view.*

class TagAdapter(val manager: Manager) : RecyclerView.Adapter<TagAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val checkbox = view.checkbox!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tags_card, parent, false))
    }

    override fun getItemCount(): Int {
        return manager.tagList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkbox.text = manager.tagList[position]
        holder.checkbox.isChecked = manager.isCheckedTag[manager.tagList[position]]!!
        holder.checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
            manager.isCheckedTag[manager.tagList[position]] = isChecked
        }
    }
}