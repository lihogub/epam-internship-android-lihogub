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
    private val likeButton = view.findViewById<ImageView>(R.id.mealListItemLikeButton)

    fun bind(
        mealUIModel: MealUIModel,
        onMealClickListener: OnMealClickListener,
        onMealLikeClickListener: OnMealLikeClickListener
    ) {
        mealTitle.text = mealUIModel.name
        Glide.with(view.context)
            .load(mealUIModel.thumbUrl)
            .into(mealImage)

        Glide.with(view.context)
            .load(
                if (mealUIModel.liked)
                    R.drawable.heart_pink
                else
                    R.drawable.heart_black
            )
            .into(likeButton)

        itemView.setOnClickListener {
            onMealClickListener.onMealClick(mealUIModel.id)
        }

        likeButton.setOnClickListener {
            onMealLikeClickListener.onMealLikeClick(mealUIModel.id)
        }
    }
}