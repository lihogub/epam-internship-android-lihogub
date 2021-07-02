package ru.lihogub.epam_internship_android_lihogub

data class MealDetailsUIModel(
    val name: String,
    val area: String,
    val tagList: List<String>,
    val ingredients: String,
    val thumbUrl: String,
    val youtubeUrl: String,
)