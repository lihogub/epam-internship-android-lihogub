package ru.lihogub.epam_internship_android_lihogub

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MealListItemHolder(view: View): RecyclerView.ViewHolder(view) {
    private val dishTitle = view.findViewById<TextView>(R.id.list_item_text)
    private val dishImage = view.findViewById<ImageView>(R.id.list_item_image)

    fun bind(dish: Dish, clickListener: OnItemClickListener) {
        dishTitle.text = dish.name
        dishImage.setBackgroundResource(dish.image)
        itemView.setOnClickListener{
            clickListener.onClick(dish)
        }
    }
}