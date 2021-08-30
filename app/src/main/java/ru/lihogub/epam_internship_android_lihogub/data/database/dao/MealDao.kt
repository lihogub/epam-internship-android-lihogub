package ru.lihogub.epam_internship_android_lihogub.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.data.database.model.MealDbModel

@Dao
interface MealDao {
    @Query("SELECT * FROM meal WHERE categoryName = :categoryName")
    fun getMealListByCategoryName(categoryName: String): Single<List<MealDbModel>>

    @Insert(onConflict = IGNORE)
    fun saveMealList(mealList: List<MealDbModel>): Completable

    @Query("SELECT * FROM meal WHERE id = :id")
    fun getMealById(id: Int): Single<MealDbModel>

    @Insert(onConflict = REPLACE)
    fun saveMeal(mealDbModel: MealDbModel): Completable
}
