package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

class MealAdapter(private val onMealClickListener: OnMealClickListener) :
    RecyclerView.Adapter<MealHolder>() {
    var mealList = listOf<MealUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        val listItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_list_item, parent, false)
        return MealHolder(listItem)
    }

    override fun onBindViewHolder(holderMeal: MealHolder, position: Int) {
        holderMeal.bind(mealList[position], onMealClickListener)
    }

    override fun getItemCount(): Int = mealList.size
}