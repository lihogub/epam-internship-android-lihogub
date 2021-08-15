package ru.lihogub.epam_internship_android_lihogub.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity

interface MealRepository {
    fun getMealList(categoryName: String): Single<List<MealEntity>>
    fun getMealDetails(mealId: Int): Single<MealDetailsEntity>
}