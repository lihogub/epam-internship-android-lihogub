package ru.lihogub.epam_internship_android_lihogub

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategoryList(): Call<CategoryList>

    @GET("filter.php")
    fun getMealList(@Query("c") categoryName: String): Call<MealList>

    @GET("lookup.php")
    fun getMealDetailsList(@Query("i") categoryId: Int): Call<MealDetailsList>
}
