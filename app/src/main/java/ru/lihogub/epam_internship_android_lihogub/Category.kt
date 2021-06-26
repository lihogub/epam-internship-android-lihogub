package ru.lihogub.epam_internship_android_lihogub

import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    var active: Boolean,
    @DrawableRes val image: Int
)
