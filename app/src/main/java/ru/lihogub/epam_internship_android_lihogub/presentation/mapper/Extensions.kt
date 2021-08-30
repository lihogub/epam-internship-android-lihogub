package ru.lihogub.epam_internship_android_lihogub.presentation.mapper

import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealDetailsUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

fun CategoryEntity.toCategoryUIModel() =
    CategoryUIModel(id, name, thumbUrl, false)

fun MealDetailsEntity.toMealDetailsUIModel() =
    MealDetailsUIModel(id, name, area.uppercase(), tagList, ingredients, thumbUrl, youtubeUrl, liked)

fun MealEntity.toMealUIModel() =
    MealUIModel(id, name, thumbUrl, liked)