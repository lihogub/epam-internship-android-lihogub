package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import androidx.recyclerview.widget.DiffUtil
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel

class CategoryDiffCallback(
    private val oldList: List<CategoryUIModel>,
    private val newList: List<CategoryUIModel>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}