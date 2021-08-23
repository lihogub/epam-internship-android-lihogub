package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetLastCategoryUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SaveLastCategoryUseCase

class MealListViewModelFactory(
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val saveLastCategoryUseCase: SaveLastCategoryUseCase,
    private val getLastCategoryUseCase: GetLastCategoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MealListViewModel(
            getMealListUseCase = getMealListUseCase,
            getCategoryListUseCase = getCategoryListUseCase,
            getLastCategoryUseCase = getLastCategoryUseCase,
            saveLastCategoryUseCase = saveLastCategoryUseCase
        ) as T
}