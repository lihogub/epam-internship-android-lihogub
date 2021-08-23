package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel.MealDetailsViewModel
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel.MealDetailsViewModelFactory

@Module
class MealDetailsModule {
    @Provides
    fun provideMealDetailsViewModelFactory(
        getMealDetailsUseCase: GetMealDetailsUseCase
    ): MealDetailsViewModelFactory =
        MealDetailsViewModelFactory(
            getMealDetailsUseCase = getMealDetailsUseCase
        )

    @Provides
    fun provideMealDetailsViewModel(
        mealDetailsViewModelFactory: MealDetailsViewModelFactory
    ): MealDetailsViewModel =
        mealDetailsViewModelFactory.create(MealDetailsViewModel::class.java)
}