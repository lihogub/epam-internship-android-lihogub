package ru.lihogub.epam_internship_android_lihogub.data.network.model

import com.google.gson.annotations.SerializedName

data class MealDetailsListDto(
    @SerializedName("meals")
    val meals: List<MealDetailsDto>
)
