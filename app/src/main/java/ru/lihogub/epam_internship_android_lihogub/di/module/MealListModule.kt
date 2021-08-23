package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetLastCategoryUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SaveLastCategoryUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModel
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModelFactory

@Module
class MealListModule {
    @Provides
    fun provideMealListViewModelFactory(
        getMealListUseCase: GetMealListUseCase,
        getCategoryListUseCase: GetCategoryListUseCase,
        saveLastCategoryUseCase: SaveLastCategoryUseCase,
        getLastCategoryUseCase: GetLastCategoryUseCase
    ): MealListViewModelFactory =
        MealListViewModelFactory(
            getMealListUseCase = getMealListUseCase,
            getCategoryListUseCase = getCategoryListUseCase,
            getLastCategoryUseCase = getLastCategoryUseCase,
            saveLastCategoryUseCase = saveLastCategoryUseCase
        )

    @Provides
    fun provideMealListViewModel(mealListViewModelFactory: MealListViewModelFactory): MealListViewModel =
        mealListViewModelFactory.create(MealListViewModel::class.java)
}