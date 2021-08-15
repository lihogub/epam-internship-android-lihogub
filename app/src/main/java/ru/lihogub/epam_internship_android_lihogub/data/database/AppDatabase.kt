package ru.lihogub.epam_internship_android_lihogub.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.data.model.database.CategoryDbModel

@Database(
    entities = [CategoryDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}