package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealDetailsUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealDetailsUIModel

class MealDetailsViewModel(
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModel() {

    private val _mealDetails = MutableLiveData<MealDetailsUIModel>()
    val mealDetails: LiveData<MealDetailsUIModel> = _mealDetails

    fun start(mealId: Int) {
        getMealDetails(mealId)
    }

    private fun getMealDetails(mealId: Int) {
        getMealDetailsUseCase(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                it.toMealDetailsUIModel()
            }
            .subscribe({
                _mealDetails.postValue(it)
            }, {
                it.printStackTrace()
            })
    }
}
