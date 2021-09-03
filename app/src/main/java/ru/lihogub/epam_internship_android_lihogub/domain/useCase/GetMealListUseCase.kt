package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

class GetMealListUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(categoryName: String) = mealRepository.getMealList(categoryName)
}