package ru.lihogub.epam_internship_android_lihogub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MealCategoryAdapter(private var lastCategory: Int, private val categoryList: List<Category>):
    RecyclerView.Adapter<MealCategoryHolder>(){
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

    private val onCategoryClickListener: OnCategoryClickListener = object : OnCategoryClickListener {
        override fun onClick(category: Category) {
            categoryList[lastCategory].active = false
            categoryList[category.id].active = true
            lastCategory = category.id
            notifyDataSetChanged()
        }
    }
}