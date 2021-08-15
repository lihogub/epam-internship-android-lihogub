package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideGetCategoryListUseCase(
        categoryRepository: CategoryRepository,
        categoryDao: CategoryDao
    ) = GetCategoryListUseCase(categoryRepository, categoryDao)

    @Provides
    fun provideGetMealListUseCase(mealRepository: MealRepository) =
        GetMealListUseCase(mealRepository)

    @Provides
    fun provideGetMealDetailsUseCase(mealRepository: MealRepository) =
        GetMealDetailsUseCase(mealRepository)
}