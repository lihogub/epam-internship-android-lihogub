package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class ApiCategory(
    @SerializedName("idCategory")
    val id: Int,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryDescription")
    val description: String,
    @SerializedName("strCategoryThumb")
    val thumbUrl: String
)
