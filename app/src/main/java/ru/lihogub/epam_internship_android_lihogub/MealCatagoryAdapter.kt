package ru.lihogub.epam_internship_android_lihogub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MealCategoryAdapter: RecyclerView.Adapter<MealCategoryHolder>(){
    var categoryList = listOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryHolder {
        val categoryItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)
        return MealCategoryHolder(categoryItem)
    }

    override fun onBindViewHolder(holder: MealCategoryHolder, position: Int) {
        holder.bind(categoryList[position], onCategoryClickListener)
    }

    override fun getItemCount(): Int = categoryList.size

    fun setPosition(position: Int) {
        categoryList.forEach{ c -> c.active = (c.id == position) }
        notifyDataSetChanged()
    }

    private val onCategoryClickListener: OnCategoryClickListener = object : OnCategoryClickListener {
        override fun onClick(category: Category) {
            setPosition(category.id)
        }
    }
}