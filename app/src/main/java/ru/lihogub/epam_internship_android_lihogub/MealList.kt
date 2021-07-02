package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class MealList(
    @SerializedName("meals")
    val meals: List<MealListItem>
)
