package ru.lihogub.epam_internship_android_lihogub.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "thumb_url")
    val thumbUrl: String,
    @ColumnInfo(name = "categoryName")
    val categoryName: String,
    @ColumnInfo(name = "liked")
    var liked: Boolean
)
