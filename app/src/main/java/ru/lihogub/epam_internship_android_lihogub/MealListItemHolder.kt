package ru.lihogub.epam_internship_android_lihogub

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MealListItemHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val mealTitle = view.findViewById<TextView>(R.id.list_item_text)
    private val mealImage = view.findViewById<ImageView>(R.id.list_item_image)

    fun bind(mealListItem: MealListItem, clickListener: OnItemClickListener) {
        mealTitle.text = mealListItem.name
        Glide.with(view.context)
            .load(mealListItem.thumbUrl)
            .into(mealImage)
        itemView.setOnClickListener{
            clickListener.onClick(mealListItem)
        }
    }
}