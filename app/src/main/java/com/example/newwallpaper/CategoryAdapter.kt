package com.example.newwallpaper

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(
    private val categoryItems: List<CategoryItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImageView: ImageView = itemView.findViewById(R.id.c_img)
        val cardView: CardView = itemView.findViewById(R.id.c_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryItem = categoryItems[position]
        val firstImageResId = categoryItem.categoryImages[0] // Birinchi rasmini olish
        holder.categoryImageView.setImageResource(firstImageResId)

        // Avtomatik tanlash va rangni o'zgartirish
        val isSelected = position == lastClickedPosition
        if (isSelected) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.selected_color))
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.default_color))
        }


        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)

            // Avtomatik tanlash va rangni o'zgartirishni yangilash
            setSelectedPosition(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    private var lastClickedPosition = RecyclerView.NO_POSITION

    fun setSelectedPosition(position: Int) {
        val previousPosition = lastClickedPosition
        lastClickedPosition = position

        // Avvalgi tanlangan va hozirgi tanlangan elementlarni o'zgartiramiz
        notifyItemChanged(previousPosition)
        notifyItemChanged(position)
    }
}