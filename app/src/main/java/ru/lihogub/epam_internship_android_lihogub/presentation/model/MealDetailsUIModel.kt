package ru.lihogub.epam_internship_android_lihogub.presentation.model

data class MealDetailsUIModel(
    val id: Int,
    val name: String,
    val area: String,
    val tagList: List<String>,
    val ingredients: String,
    val thumbUrl: String,
    val youtubeUrl: String,
    val liked: Boolean
)