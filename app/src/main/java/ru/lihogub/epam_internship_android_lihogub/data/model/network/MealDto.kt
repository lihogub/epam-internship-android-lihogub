package ru.lihogub.epam_internship_android_lihogub.data.model.network

import com.google.gson.annotations.SerializedName

data class MealDto(
    @SerializedName("idMeal")
    val id: Int,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val thumbUrl: String
)