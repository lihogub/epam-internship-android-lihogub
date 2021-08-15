package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.database.dao.CategoryDao
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toCategoryDbModel
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.mapper.toCategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository

class GetCategoryListUseCase(
    private val categoryRepository: CategoryRepository,
    private val categoryDao: CategoryDao
) {
    fun getCategoryList(): Single<List<CategoryEntity>> = categoryDao.getCategoryList()
        .flatMap { dbCategoryList ->
            Single.just(
                if (dbCategoryList.isNotEmpty()) {
                    Log.d("MYTAG", "fetched from DB")
                    dbCategoryList.map { it.toCategoryEntity() }
                } else {
                    Log.d("MYTAG", "fetched from API")
                    val apiCategoryEntityList = categoryRepository.getCategoryList()
                        .blockingGet()
                    categoryDao.insertCategoryList(apiCategoryEntityList.map { it.toCategoryDbModel() })
                        .blockingAwait()
                    apiCategoryEntityList
                }
            )
        }
}
