package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class MealListFragment : Fragment(R.layout.fragment_meal_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.meal_item).setOnClickListener{
            val dish = Api.getRecipeById(52992) // id was taken from real API service
            openMealDetailsFragment(dish)
        }
        view.findViewById<LinearLayout>(R.id.steak_item).setOnClickListener{
            val dish = Api.getRecipeById(52935) // id was taken from real API service
            openMealDetailsFragment(dish)
        }
    }

    private fun openMealDetailsFragment(dish: Dish) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentHostContainer,
                MealDetailsFragment.newInstance(dish.name, dish.cuisine, dish.ingridients)
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = MealListFragment()
    }
}