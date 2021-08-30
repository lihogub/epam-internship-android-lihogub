package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import io.reactivex.rxjava3.core.Completable
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

class ResetMealLikeUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(mealId: Int): Completable = mealRepository.resetMealLiked(mealId)
}
