package ru.lihogub.epam_internship_android_lihogub

import androidx.annotation.DrawableRes


data class Dish(
    val name: String,
    val cuisine: String,
    val ingridients: String,
    @DrawableRes val image: Int
)
