package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.*
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModel
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModelFactory

@Module
class MealListModule {
    @Provides
    fun provideMealListViewModelFactory(
        getMealListUseCase: GetMealListUseCase,
        getCategoryListUseCase: GetCategoryListUseCase,
        saveLastCategoryUseCase: SaveLastCategoryUseCase,
        getLastCategoryUseCase: GetLastCategoryUseCase,
        setMealLikeUseCase: SetMealLikeUseCase,
        resetMealLikeUseCase: ResetMealLikeUseCase
    ): MealListViewModelFactory =
        MealListViewModelFactory(
            getMealListUseCase = getMealListUseCase,
            getCategoryListUseCase = getCategoryListUseCase,
            getLastCategoryUseCase = getLastCategoryUseCase,
            saveLastCategoryUseCase = saveLastCategoryUseCase,
            setMealLikeUseCase = setMealLikeUseCase,
            resetMealLikeUseCase = resetMealLikeUseCase
        )

    @Provides
    fun provideMealListViewModel(mealListViewModelFactory: MealListViewModelFactory): MealListViewModel =
        mealListViewModelFactory.create(MealListViewModel::class.java)
}