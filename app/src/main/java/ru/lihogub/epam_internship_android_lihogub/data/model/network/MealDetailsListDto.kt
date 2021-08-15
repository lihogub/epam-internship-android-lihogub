package ru.lihogub.epam_internship_android_lihogub.data.model.network

import com.google.gson.annotations.SerializedName

data class MealDetailsListDto(
    @SerializedName("meals")
    val meals: List<MealDetailsDto>
)
