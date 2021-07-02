package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("categories")
    val categories: List<Category>
)
