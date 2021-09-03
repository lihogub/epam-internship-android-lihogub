package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.ResetMealLikeUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SetMealLikeUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel.MealDetailsViewModel

@Module
class MealDetailsModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideMealDetailsViewModelFactory(
        getMealDetailsUseCase: GetMealDetailsUseCase,
        setMealLikeUseCase: SetMealLikeUseCase,
        resetMealLikeUseCase: ResetMealLikeUseCase
    ): MealDetailsViewModel = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MealDetailsViewModel(
                getMealDetailsUseCase,
                setMealLikeUseCase,
                resetMealLikeUseCase
            ) as T
    }
        .create(MealDetailsViewModel::class.java)
}