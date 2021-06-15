package ru.lihogub.epam_internship_android_lihogub

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class MealDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        val cuisine: TextView = findViewById(R.id.cuisine)
        cuisine.text = intent.getStringExtra(CUISINE)

        val name: TextView = findViewById(R.id.name)
        name.text = intent.getStringExtra(NAME)

        val ingridients: TextView = findViewById(R.id.ingridients)
        ingridients.text = intent.getStringExtra(INGRIDIENTS)
    }

    companion object {
        private const val NAME = "NAME"
        private const val INGRIDIENTS = "INGRIDIENTS"
        private const val CUISINE = "CUISINE"
        fun getIntent(context: Context, name: String, cuisine: String, ingridients: String): Intent {
            val newIntent = Intent(context, MealDetailsActivity::class.java)
            newIntent.putExtra(NAME, name)
            newIntent.putExtra(CUISINE, cuisine)
            newIntent.putExtra(INGRIDIENTS, ingridients)
            return newIntent
        }
    }
}