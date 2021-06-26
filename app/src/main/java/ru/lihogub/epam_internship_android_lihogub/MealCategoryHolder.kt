package ru.lihogub.epam_internship_android_lihogub

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MealCategoryHolder(view: View): RecyclerView.ViewHolder(view) {
    private val categoryImage = view.findViewById<ImageView>(R.id.category_list_item_image)
    private val categoryCard = view.findViewById<CardView>(R.id.category_list_item_card)

    fun bind(category: Category, clickListener: OnCategoryClickListener) {
        categoryImage.setImageResource(category.image)
        val bgColor = if (category.active) R.color.pink else R.color.blue_light
        categoryCard.setCardBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
        itemView.setOnClickListener{
            clickListener.onClick(category)
        }
    }
}
