package ru.lihogub.epam_internship_android_lihogub

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategoryList(): Single<CategoryListDto>

    @GET("filter.php")
    fun getMealList(@Query("c") categoryName: String): Single<MealList>

    @GET("lookup.php")
    fun getMealDetailsList(@Query("i") categoryId: Int): Single<MealDetailsList>
}
