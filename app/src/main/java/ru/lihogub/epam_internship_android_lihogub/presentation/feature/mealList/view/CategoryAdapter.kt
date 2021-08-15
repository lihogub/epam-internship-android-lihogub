package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel

class CategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryHolder>() {
    var categoryList = listOf<CategoryUIModel>()
    var currentCategoryName: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val categoryItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)
        return CategoryHolder(categoryItem, onCategoryClickListener)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryUIModel = categoryList[position]
        holder.bind(categoryUIModel, categoryUIModel.name == currentCategoryName)
    }

    override fun getItemCount(): Int = categoryList.size
}
