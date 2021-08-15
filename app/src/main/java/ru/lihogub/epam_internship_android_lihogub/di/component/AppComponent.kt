package ru.lihogub.epam_internship_android_lihogub.di.component

import android.content.Context

import dagger.BindsInstance
import dagger.Component
import ru.lihogub.epam_internship_android_lihogub.di.module.*
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.view.MealDetailsFragment
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view.MealListFragment
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        MealApiModule::class,
        BindModule::class,
        DatabaseModule::class,
        UseCaseModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(mealListFragment: MealListFragment)
    fun inject(mealDetailsFragment: MealDetailsFragment)

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }
}
