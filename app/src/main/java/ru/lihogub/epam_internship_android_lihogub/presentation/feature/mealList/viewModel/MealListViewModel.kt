package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealEntity
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toCategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.CategoryUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealUIModel

class MealListViewModel(
    myApplication: Application,
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : AndroidViewModel(myApplication) {

    private val prefs: SharedPreferences by lazy {
        myApplication.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    private var lastCategoryName: String
        get() = prefs.getString("lastCategoryName", "") ?: ""
        set(value) = prefs.edit().putString("lastCategoryName", value).apply()

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
        openCategory(lastCategoryName)
        _currentCategory.postValue(lastCategoryName)
    }

    fun openCategory(categoryName: String) {
        _currentCategory.postValue(categoryName)
        getMealList(categoryName)
        lastCategoryName = categoryName
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

enum class SortingRule { ASC, DESC, DEFAULT }

class MealListViewModelFactory(
    private val myApplication: Application,
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MealListViewModel(myApplication, getMealListUseCase, getCategoryListUseCase) as T
}
