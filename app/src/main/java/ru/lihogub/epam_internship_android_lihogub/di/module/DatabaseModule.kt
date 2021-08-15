package ru.lihogub.epam_internship_android_lihogub.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.data.database.AppDatabase
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao

@Module
class DatabaseModule {
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "category_db").build()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao = appDatabase.getCategoryDao()
}