package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.*

@Module
class UseCaseModule {
    @Provides
    fun provideGetCategoryListUseCase(categoryRepository: CategoryRepository) =
        GetCategoryListUseCase(categoryRepository)

    @Provides
    fun provideGetMealListUseCase(mealRepository: MealRepository) =
        GetMealListUseCase(mealRepository)

    @Provides
    fun provideGetMealDetailsUseCase(mealRepository: MealRepository) =
        GetMealDetailsUseCase(mealRepository)

    @Provides
    fun provideGetLastCategoryUseCase(categoryRepository: CategoryRepository) =
        GetLastCategoryUseCase(categoryRepository)

    @Provides
    fun provideSaveLastCategoryUseCase(categoryRepository: CategoryRepository) =
        SaveLastCategoryUseCase(categoryRepository)

    @Provides
    fun provideSetMealLikeUseCase(mealRepository: MealRepository) =
        SetMealLikeUseCase(mealRepository)

    @Provides
    fun provideResetMealLikeUseCase(mealRepository: MealRepository) =
        ResetMealLikeUseCase(mealRepository)
}