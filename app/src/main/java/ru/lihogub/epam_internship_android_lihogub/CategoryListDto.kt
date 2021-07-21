package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class CategoryListDto(
    @SerializedName("categories")
    val categories: List<Category>
)
