package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel

class CategoryHolder(
    private val view: View,
    private val onCategoryClickListener: OnCategoryClickListener
) : RecyclerView.ViewHolder(view) {
    private val categoryImage = view.findViewById<ImageView>(R.id.categoryImage)
    private val categoryCard = view.findViewById<CardView>(R.id.categoryCard)

    fun bind(category: CategoryUIModel, isActive: Boolean) {
        Glide.with(view.context)
            .load(category.thumbUrl)
            .into(categoryImage)

        val bgColor = if (isActive) R.color.pink else R.color.blue_light
        categoryCard.setCardBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))

        itemView.setOnClickListener {
            onCategoryClickListener.onCategoryClick(category.name)
        }
    }
}
