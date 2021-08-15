package ru.lihogub.epam_internship_android_lihogub.data.repository

import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity
import ru.lihogub.epam_internship_android_lihogub.domain.mapper.toMealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.mapper.toMealEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    var mealApi: MealApi
) : MealRepository {
    override fun getMealList(categoryName: String): Single<List<MealEntity>> =
        mealApi.getMealList(categoryName)
            .flatMap { mealListDto ->
                Single.just(
                    mealListDto.meals.map {
                        it.toMealEntity()
                    }
                )
            }

    override fun getMealDetails(mealId: Int): Single<MealDetailsEntity> =
        mealApi.getMealDetails(mealId)
            .flatMap { mealDetailsListDto ->
                Single.just(
                    mealDetailsListDto.meals.map {
                        it.toMealDetailsEntity()
                    }
                        .first()
                )
            }
}