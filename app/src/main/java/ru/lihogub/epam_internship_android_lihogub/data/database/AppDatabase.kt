package ru.lihogub.epam_internship_android_lihogub.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.MealDao
import ru.lihogub.epam_internship_android_lihogub.data.database.model.CategoryDbModel
import ru.lihogub.epam_internship_android_lihogub.data.database.model.MealDbModel

@Database(
    entities = [CategoryDbModel::class, MealDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getMealDao(): MealDao
}