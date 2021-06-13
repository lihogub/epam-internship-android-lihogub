package ru.lihogub.epam_internship_android_lihogub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MealListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)

        val mealItem: LinearLayout = findViewById(R.id.meal_item)
        mealItem.setOnClickListener{
            val dish = API.getRecipeById(52992) // id was taken from real API service
            startActivity(MealDetailsActivity.getIntent(this, dish.name, dish.cuisine.uppercase(), dish.instruction))
        }

        val steakItem: LinearLayout = findViewById(R.id.steak_item)
        steakItem.setOnClickListener{
            val dish = API.getRecipeById(52935) // id was taken from real API service
            startActivity(MealDetailsActivity.getIntent(this, dish.name, dish.cuisine.uppercase(), dish.instruction))
        }
    }
}