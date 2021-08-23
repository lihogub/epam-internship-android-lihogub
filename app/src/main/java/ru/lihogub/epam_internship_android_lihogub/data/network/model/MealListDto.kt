package ru.lihogub.epam_internship_android_lihogub.data.network.model

import com.google.gson.annotations.SerializedName

data class MealListDto(
    @SerializedName("meals")
    val meals: List<MealDto>
)
