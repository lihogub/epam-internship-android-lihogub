package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase

class MealDetailsViewModelFactory(
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MealDetailsViewModel(getMealDetailsUseCase) as T
}