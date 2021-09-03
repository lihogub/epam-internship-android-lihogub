package ru.lihogub.epam_internship_android_lihogub.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.lihogub.epam_internship_android_lihogub.data.prefs.CategoryPrefsSource

@Module
class PrefsModule {
    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    @Provides
    fun provideCategoryPrefsSource(prefs: SharedPreferences) = CategoryPrefsSource(prefs)
}