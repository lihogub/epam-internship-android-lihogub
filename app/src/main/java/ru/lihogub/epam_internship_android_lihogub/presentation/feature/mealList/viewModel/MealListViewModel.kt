package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.*
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toCategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

class MealListViewModel(
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getLastCategoryUseCase: GetLastCategoryUseCase,
    private val saveLastCategoryUseCase: SaveLastCategoryUseCase,
    private val setMealLikeUseCase: SetMealLikeUseCase,
    private val resetMealLikeUseCase: ResetMealLikeUseCase
) : ViewModel() {

    private val _mealList = MutableLiveData<List<MealUIModel>>(listOf())
    val mealList: LiveData<List<MealUIModel>> = _mealList

    private val _categoryList = MutableLiveData<List<CategoryUIModel>>(listOf())
    val categoryList: LiveData<List<CategoryUIModel>> = _categoryList

    private val _currentCategory = MutableLiveData("")
    val currentCategory: LiveData<String> = _currentCategory

    private val _sortingRule = MutableLiveData(SortingRule.DEFAULT)
    val sortingRule: LiveData<SortingRule> = _sortingRule

    fun start() {
        getCategoryList()
        val lastCategoryName = getLastCategoryUseCase()
        openCategory(lastCategoryName)
        _currentCategory.postValue(lastCategoryName)
    }

    fun openCategory(categoryName: String) {
        _currentCategory.postValue(categoryName)
        getMealList(categoryName)
        saveLastCategoryUseCase(categoryName)
    }

    fun applySort() {
        _mealList.value?.let {
            _mealList.setValue(
                when (sortingRule.value) {
                    SortingRule.ASC -> it.sortedBy { meal -> meal.name }
                    SortingRule.DESC -> it.sortedByDescending { meal -> meal.name }
                    else -> it.sortedBy { meal -> meal.id }
                }
            )
        }
    }

    fun sortMealsByAscending() = _sortingRule.postValue(SortingRule.ASC)

    fun sortMealsByDescending() = _sortingRule.postValue(SortingRule.DESC)

    fun toggleLike(mealId: Int) {
        val meal = mealList.value?.find {
            it.id == mealId
        }
        if (meal?.liked == true) {
            resetLike(mealId)
        } else {
            setLike(mealId)
        }
    }

    private fun setLike(mealId: Int) =
        setMealLikeUseCase(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("MYTAG", "Like set")
            }, {
                it.printStackTrace()
            })

    private fun resetLike(mealId: Int) =
        resetMealLikeUseCase(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("MYTAG", "Like reset")
            }, {
                it.printStackTrace()
            })

    private fun getMealList(categoryName: String) {
        getMealListUseCase(categoryName)
            .map {
                it.map { mealEntity -> mealEntity.toMealUIModel() }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mealList.setValue(it)
            }, {
                it.printStackTrace()
            })
    }

    private fun getCategoryList() {
        getCategoryListUseCase()
            .map {
                it.map { categoryEntity -> categoryEntity.toCategoryUIModel() }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _categoryList.setValue(it)
            }, {
                it.printStackTrace()
            })
    }
}
