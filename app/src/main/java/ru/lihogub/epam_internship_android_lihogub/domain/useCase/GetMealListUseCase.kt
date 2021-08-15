package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

class GetMealListUseCase(private val mealRepository: MealRepository) {
    fun getMealList(categoryName: String) = mealRepository.getMealList(categoryName)
}