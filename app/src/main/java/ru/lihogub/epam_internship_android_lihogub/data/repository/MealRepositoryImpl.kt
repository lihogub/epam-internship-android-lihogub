package ru.lihogub.epam_internship_android_lihogub.data.repository

import android.util.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.MealDao
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toMealDbModel
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toMealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toMealEntity
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.MealRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao
) : MealRepository {

    override fun getMealList(categoryName: String): Flowable<List<MealEntity>> = mealDao
        .getMealListByCategoryName(categoryName)
        .map { mealDbList -> mealDbList.map { it.toMealEntity() } }
        .flatMap { mealEntityListFromDb ->
            if (mealEntityListFromDb.isNotEmpty()) {
                Log.e("LOL", "Meals fetched from DB")
                Flowable.just(mealEntityListFromDb)
            } else {
                Flowable.fromCallable {
                    Log.e("LOL", "Meals fetched from API")
                    mealApi
                        .getMealList(categoryName)
                        .map { it.meals }
                        .map { mealDtoListFromApi -> mealDtoListFromApi.map { it.toMealEntity() } }
                        .blockingGet()
                        .apply {
                            mealDao
                                .saveMealList(this.map { it.toMealDbModel(categoryName) })
                                .blockingSubscribe()
                        }
                }
            }
        }

    override fun getMealDetails(mealId: Int): Single<MealDetailsEntity> = mealApi
        .getMealDetails(mealId)
        .map { it.meals }
        .map { it.first() }
        .map { mealDetailsListDto -> mealDetailsListDto.toMealDetailsEntity() }
        .flatMap { mealEntityModel ->
            mealDao
                .getMealById(mealId)
                .map { mealDbModel -> mealEntityModel.apply { liked = mealDbModel.liked } }
        }

    override fun setMealLiked(mealId: Int): Completable = updateMealLiked(mealId, true)

    override fun resetMealLiked(mealId: Int): Completable = updateMealLiked(mealId, false)

    private fun updateMealLiked(mealId: Int, liked: Boolean) =
        mealDao.setLikedByMealId(mealId, liked)
}