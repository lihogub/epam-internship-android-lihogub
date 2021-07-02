package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class MealListItem (
    @SerializedName("idMeal")
    val id: Int,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val thumbUrl: String
)