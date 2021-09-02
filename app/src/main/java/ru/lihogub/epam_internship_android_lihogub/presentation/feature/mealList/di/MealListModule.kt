package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.*
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModel

@Module
class MealListModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideMealListViewModelFactory(
        getMealListUseCase: GetMealListUseCase,
        getCategoryListUseCase: GetCategoryListUseCase,
        saveLastCategoryUseCase: SaveLastCategoryUseCase,
        getLastCategoryUseCase: GetLastCategoryUseCase,
        setMealLikeUseCase: SetMealLikeUseCase,
        resetMealLikeUseCase: ResetMealLikeUseCase
    ): MealListViewModel = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MealListViewModel(
                getMealListUseCase = getMealListUseCase,
                getCategoryListUseCase = getCategoryListUseCase,
                getLastCategoryUseCase = getLastCategoryUseCase,
                saveLastCategoryUseCase = saveLastCategoryUseCase,
                setMealLikeUseCase = setMealLikeUseCase,
                resetMealLikeUseCase = resetMealLikeUseCase
            ) as T
    }
        .create(MealListViewModel::class.java)
}