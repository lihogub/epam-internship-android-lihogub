package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealDetailsUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.ResetMealLikeUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.SetMealLikeUseCase
import ru.lihogub.epam_internship_android_lihogub.presentation.mapper.toMealDetailsUIModel
import ru.lihogub.epam_internship_android_lihogub.presentation.model.MealDetailsUIModel

class MealDetailsViewModel(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val setMealLikeUseCase: SetMealLikeUseCase,
    private val resetMealLikeUseCase: ResetMealLikeUseCase
) : ViewModel() {

    private val _mealDetails = MutableLiveData<MealDetailsUIModel>()
    val mealDetails: LiveData<MealDetailsUIModel> = _mealDetails

    fun start(mealId: Int) {
        getMealDetails(mealId)
    }

    fun toggleLike() {
        val currentMealDetails = mealDetails.value ?: return

        if (currentMealDetails.liked) {
            resetLike(currentMealDetails.id)
        } else {
            setLike(currentMealDetails.id)
        }
    }

    private fun setLike(mealId: Int) = setMealLikeUseCase(mealId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({

        }, {
            it.printStackTrace()
        })

    private fun resetLike(mealId: Int) = resetMealLikeUseCase(mealId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({

        }, {
            it.printStackTrace()
        })

    private fun getMealDetails(mealId: Int) {
        getMealDetailsUseCase(mealId)
            .map {
                it.toMealDetailsUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mealDetails.setValue(it)
            }, {
                it.printStackTrace()
            })
    }
}
