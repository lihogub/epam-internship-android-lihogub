package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel

import android.app.Application
import androidx.lifecycle.*
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.entity.MealDetailsEntity
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealDetailsUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealDetailsUIModel

class MealDetailsViewModel(
    application: Application,
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) :
    AndroidViewModel(application) {

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

class MealDetailsViewModelFactory(
    private val application: Application,
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MealDetailsViewModel(application, getMealDetailsUseCase) as T

}