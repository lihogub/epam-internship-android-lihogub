package ru.lihogub.epam_internship_android_lihogub.data.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toCategoryDbModel
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toCategoryEntity
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.data.prefs.CategoryPrefsSource
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    var mealApi: MealApi,
    var categoryDao: CategoryDao,
    var categoryPrefsSource: CategoryPrefsSource
) : CategoryRepository {
    override fun getCategoryList(): Single<List<CategoryEntity>> = categoryDao.getCategoryList()
        .map { dbCategoryList ->
            dbCategoryList.map { it.toCategoryEntity() }
        }
        .flatMap { dbCategoryList ->
            if (dbCategoryList.isNotEmpty()) {
                Log.d("MYTAG", "CategoryList fetched from DB")
                Single.just(dbCategoryList)
            } else {
                Log.d("MYTAG", "CategoryList fetched from API")
                Single.fromCallable {
                    val apiCategoryEntityList = mealApi.getCategoryList()
                        .blockingGet()
                        .categories
                        .map { it.toCategoryEntity() }
                    categoryDao
                        .insertCategoryList(
                            apiCategoryEntityList.map {
                                it.toCategoryDbModel()
                            }
                        )
                        .blockingAwait()
                    apiCategoryEntityList
                }
            }
        }

    override fun setLastCategoryName(categoryName: String) =
        categoryPrefsSource.setLastCategoryName(categoryName)

    override fun getLastCategoryName(): String =
        categoryPrefsSource.getLastCategoryName()
}