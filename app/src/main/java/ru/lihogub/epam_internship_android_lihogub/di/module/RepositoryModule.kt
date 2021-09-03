package ru.lihogub.epam_internship_android_lihogub.di.module

import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.MealDao
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.data.prefs.CategoryPrefsSource
import ru.lihogub.epam_internship_android_lihogub.data.repository.CategoryRepositoryImpl
import ru.lihogub.epam_internship_android_lihogub.data.repository.MealRepositoryImpl

@Module
class RepositoryModule {
    @Provides
    fun provideMealRepositoryImpl(mealApi: MealApi, mealDao: MealDao) = MealRepositoryImpl(mealApi, mealDao)

    @Provides
    fun provideCategoryRepositoryImpl(
        mealApi: MealApi,
        categoryDao: CategoryDao,
        categoryPrefsSource: CategoryPrefsSource
    ) =
        CategoryRepositoryImpl(mealApi, categoryDao, categoryPrefsSource)
}