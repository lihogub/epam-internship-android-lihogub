package ru.lihogub.epam_internship_android_lihogub.data.mapper

import ru.lihogub.epam_internship_android_lihogub.data.database.model.CategoryDbModel
import ru.lihogub.epam_internship_android_lihogub.data.network.model.CategoryDto
import ru.lihogub.epam_internship_android_lihogub.data.network.model.MealDetailsDto
import ru.lihogub.epam_internship_android_lihogub.data.network.model.MealDto
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity

fun CategoryEntity.toCategoryDbModel() =
    CategoryDbModel(id, name, description, thumbUrl)

fun MealDetailsDto.toMealDetailsEntity(): MealDetailsEntity =
    MealDetailsEntity(
        name = name,
        area = area,
        tagList = tags?.split(",") ?: listOf(),
        ingredients = listOf(
            ingredient1 to measure1,
            ingredient2 to measure2,
            ingredient3 to measure3,
            ingredient4 to measure4,
            ingredient5 to measure5,
            ingredient6 to measure6,
            ingredient7 to measure7,
            ingredient8 to measure8,
            ingredient9 to measure9,
            ingredient10 to measure10,
            ingredient11 to measure11,
            ingredient12 to measure12,
            ingredient13 to measure13,
            ingredient14 to measure14,
            ingredient15 to measure15,
            ingredient16 to measure16,
            ingredient17 to measure17,
            ingredient18 to measure18,
            ingredient19 to measure19,
            ingredient20 to measure20
        )
            .filter { !it.first.isNullOrEmpty() && !it.second.isNullOrEmpty() }
            .map { pair -> pair.first + " " + pair.second }
            .reduceRight { s, acc -> acc.plus("\n").plus(s) },
        thumbUrl = thumbUrl,
        youtubeUrl = youtubeUrl
    )

fun MealDto.toMealEntity() =
    MealEntity(id, name, thumbUrl)

fun CategoryDto.toCategoryEntity() =
    CategoryEntity(id, name, description, thumbUrl)

fun CategoryDbModel.toCategoryEntity() =
    CategoryEntity(id, name, description, thumbUrl)