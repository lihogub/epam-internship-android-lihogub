package ru.lihogub.epam_internship_android_lihogub

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategoryList(): Single<List<Category>>

    @Insert
    fun insertCategoryList(categoryList: List<Category>): Completable
}