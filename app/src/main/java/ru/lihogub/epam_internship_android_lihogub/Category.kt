package ru.lihogub.epam_internship_android_lihogub

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("idCategory")
    val id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("strCategory")
    val name: String,
    @ColumnInfo(name = "description")
    @SerializedName("strCategoryDescription")
    val description: String,
    @ColumnInfo(name = "thumb_url")
    @SerializedName("strCategoryThumb")
    val thumbUrl: String,
    @ColumnInfo(name = "active")
    var active: Boolean = false
)
