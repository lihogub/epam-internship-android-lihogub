package ru.lihogub.epam_internship_android_lihogub.data.repository

import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.mapper.toCategoryEntity
import ru.lihogub.epam_internship_android_lihogub.data.network.MealApi
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    var mealApi: MealApi
) : CategoryRepository {
    override fun getCategoryList(): Single<List<CategoryEntity>> = mealApi.getCategoryList()
        .flatMap { categoryListDto ->
            Single.just(
                categoryListDto.categories.map {
                    it.toCategoryEntity()
                }
            )
        }
}