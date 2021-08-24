package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel

class CategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryHolder>() {
    private val categoryList = mutableListOf<CategoryUIModel>()
    private var currentCategoryName: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val categoryItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)
        return CategoryHolder(categoryItem, onCategoryClickListener)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryUIModel = categoryList[position]
        holder.bind(categoryUIModel)
    }

    override fun getItemCount(): Int = categoryList.size

    fun setCategoryList(newCategoryList: List<CategoryUIModel>) {
        setData(newCategoryList)
    }

    fun setCurrentCategoryName(categoryName: String) {
        currentCategoryName = categoryName
        setData(categoryList)
    }

    private fun selectCategory(newCategoryList: List<CategoryUIModel>) = newCategoryList
        .map {
            it.copy()
        }
        .map { category ->
            category.apply {
                active = (category.name == currentCategoryName)
            }
        }

    private fun setData(newCategoryList: List<CategoryUIModel>) {
        val categoryListWithSelectedActive = selectCategory(newCategoryList)
        val diffCallback = CategoryDiffCallback(categoryList, categoryListWithSelectedActive)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        categoryList.clear()
        categoryList.addAll(categoryListWithSelectedActive)
        diffResult.dispatchUpdatesTo(this)
    }
}
