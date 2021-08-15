package ru.lihogub.epam_internship_android_lihogub.domain.entity

data class MealDetailsEntity(
    val name: String,
    val area: String,
    val tagList: List<String>,
    val ingredients: String,
    val thumbUrl: String,
    val youtubeUrl: String,
)
