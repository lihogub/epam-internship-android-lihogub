package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

class GetMealDetailsUseCase(private val mealRepository: MealRepository) {
    fun getMealDetails(mealId: Int) = mealRepository.getMealDetails(mealId)
}
