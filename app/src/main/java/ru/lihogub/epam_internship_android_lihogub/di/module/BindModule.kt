package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Binds
import dagger.Module
import ru.lihogub.epam_internship_android_lihogub.data.repository.CategoryRepositoryImpl
import ru.lihogub.epam_internship_android_lihogub.data.repository.MealRepositoryImpl
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository

@Module
interface BindModule {
    @Binds
    fun bindMealRepositoryProvide(mealRepositoryImpl: MealRepositoryImpl): MealRepository

    @Binds
    fun bindCategoryRepositoryProvide(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}