package ru.lihogub.epam_internship_android_lihogub.data.mapper

import ru.lihogub.epam_internship_android_lihogub.data.model.database.CategoryDbModel
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity

fun CategoryEntity.toCategoryDbModel() =
    CategoryDbModel(id, name, description, thumbUrl)

