package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class HostActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activityHostContainer, HostFragment.newInstance())
            .commit()
    }
}