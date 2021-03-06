package ru.lihogub.epam_internship_android_lihogub.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.lihogub.epam_internship_android_lihogub.data.network.model.CategoryListDto
import ru.lihogub.epam_internship_android_lihogub.data.network.model.MealDetailsListDto
import ru.lihogub.epam_internship_android_lihogub.data.network.model.MealListDto

interface MealApi {
    @GET("categories.php")
    fun getCategoryList(): Single<CategoryListDto>

    @GET("filter.php")
    fun getMealList(@Query("c") categoryName: String): Single<MealListDto>

    @GET("lookup.php")
    fun getMealDetails(@Query("i") mealId: Int): Single<MealDetailsListDto>
}
