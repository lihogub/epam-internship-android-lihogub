package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.data.repository.CategoryRepositoryImpl
import ru.lihogub.epam_internship_android_lihogub.data.repository.MealRepositoryImpl

@Module
class RepositoryModule {
    @Provides
    fun provideMealRepositoryImpl(mealApi: MealApi) = MealRepositoryImpl(mealApi)

    @Provides
    fun provideCategoryRepositoryImpl(mealApi: MealApi) = CategoryRepositoryImpl(mealApi)
}