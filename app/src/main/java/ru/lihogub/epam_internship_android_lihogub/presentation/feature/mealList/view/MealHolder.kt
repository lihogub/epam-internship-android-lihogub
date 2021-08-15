package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

class MealHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val mealTitle = view.findViewById<TextView>(R.id.mealListItemText)
    private val mealImage = view.findViewById<ImageView>(R.id.mealListItemImage)

    fun bind(mealUIModel: MealUIModel, onMealClickListener: OnMealClickListener) {
        mealTitle.text = mealUIModel.name
        Glide.with(view.context)
            .load(mealUIModel.thumbUrl)
            .into(mealImage)
        itemView.setOnClickListener {
            onMealClickListener.onMealClick(mealUIModel.id)
        }
    }
}