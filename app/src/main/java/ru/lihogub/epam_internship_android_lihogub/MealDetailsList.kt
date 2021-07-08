package ru.lihogub.epam_internship_android_lihogub

import com.google.gson.annotations.SerializedName

data class MealDetailsList(
    @SerializedName("meals")
    val meals: List<MealDetails>
)
