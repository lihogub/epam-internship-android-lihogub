package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetLastCategoryUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SaveLastCategoryUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toCategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

class MealListViewModel(
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getLastCategoryUseCase: GetLastCategoryUseCase,
    private val saveLastCategoryUseCase: SaveLastCategoryUseCase
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
            _mealList.postValue(
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

    private fun getMealList(categoryName: String) {
        getMealListUseCase(categoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                it.map { mealEntity -> mealEntity.toMealUIModel() }
            }
            .subscribe({
                _mealList.postValue(it)
            }, {
                it.printStackTrace()
            })
    }

    private fun getCategoryList() {
        getCategoryListUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                it.map { categoryEntity -> categoryEntity.toCategoryUIModel() }
            }
            .subscribe({
                _categoryList.postValue(it)
            }, {
                it.printStackTrace()
            })
    }
}
