package ru.lihogub.epam_internship_android_lihogub.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity

interface CategoryRepository {
    fun getCategoryList(): Single<List<CategoryEntity>>
    fun setLastCategoryName(categoryName: String)
    fun getLastCategoryName(): String
}