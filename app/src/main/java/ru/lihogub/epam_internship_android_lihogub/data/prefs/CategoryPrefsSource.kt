package ru.lihogub.epam_internship_android_lihogub.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

private const val LAST_CATEGORY_NAME = "last-category-name"

class CategoryPrefsSource @Inject constructor(private val prefs: SharedPreferences) {
    fun setLastCategoryName(categoryName: String) =
        prefs.edit().putString(LAST_CATEGORY_NAME, categoryName).apply()

    fun getLastCategoryName(): String =
        prefs.getString(LAST_CATEGORY_NAME, "") ?: ""
}
