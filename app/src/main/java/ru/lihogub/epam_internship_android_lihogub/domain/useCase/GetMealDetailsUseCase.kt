package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

class GetMealDetailsUseCase(private val mealRepository: MealRepository) {
    operator fun invoke (mealId: Int) = mealRepository.getMealDetails(mealId)
}
