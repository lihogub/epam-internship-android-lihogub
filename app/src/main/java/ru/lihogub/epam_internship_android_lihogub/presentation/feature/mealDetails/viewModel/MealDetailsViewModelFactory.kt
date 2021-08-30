package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.ResetMealLikeUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SetMealLikeUseCase

class MealDetailsViewModelFactory(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val setMealLikeUseCase: SetMealLikeUseCase,
    private val resetMealLikeUseCase: ResetMealLikeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MealDetailsViewModel(getMealDetailsUseCase, setMealLikeUseCase, resetMealLikeUseCase) as T
}