package ru.lihogub.epam_internship_android_lihogub

import android.app.Application
import android.content.Context
import ru.lihogub.epam_internship_android_lihogub.di.component.AppComponent
import ru.lihogub.epam_internship_android_lihogub.di.component.DaggerAppComponent

class MealApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
            .builder()
            .bindContext(applicationContext)
            .build()
    }

    companion object {
        lateinit var instance: MealApp
    }
}

fun Context.getAppComponent() =
    when (this) {
        is MealApp -> appComponent
        else -> (this.applicationContext as MealApp).appComponent
    }