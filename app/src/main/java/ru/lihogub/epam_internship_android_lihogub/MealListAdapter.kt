package ru.lihogub.epam_internship_android_lihogub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MealListAdapter(private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MealListItemHolder>() {
    val list = mutableListOf<Dish>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListItemHolder {
        val listItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_list_item, parent, false)
        return MealListItemHolder(listItem)
    }

    override fun onBindViewHolder(holderMeal: MealListItemHolder, position: Int) {
        holderMeal.bind(list[position], onItemClickListener)
    }

    override fun getItemCount(): Int = list.size
}