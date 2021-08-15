package ru.lihogub.epam_internship_android_lihogub.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lihogub.epam_internship_android_lihogub.R

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
    }
}