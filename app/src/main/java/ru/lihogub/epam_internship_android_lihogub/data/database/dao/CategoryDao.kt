package ru.lihogub.epam_internship_android_lihogub.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.database.model.CategoryDbModel

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategoryList(): Single<List<CategoryDbModel>>

    @Insert(onConflict = IGNORE)
    fun insertCategoryList(categoryList: List<CategoryDbModel>): Completable
}