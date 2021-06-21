package ru.lihogub.epam_internship_android_lihogub

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.cuisine).text = arguments?.getString(CUISINE)
        view.findViewById<TextView>(R.id.name).text = arguments?.getString(NAME)
        view.findViewById<TextView>(R.id.ingridients).text = arguments?.getString(INGRIDIENTS)
    }

    companion object {
        private const val NAME = "NAME"
        private const val INGRIDIENTS = "INGRIDIENTS"
        private const val CUISINE = "CUISINE"
        fun newInstance(name: String, cuisine: String, ingridients: String) = MealDetailsFragment().apply {
            arguments = bundleOf(
                NAME to name,
                CUISINE to cuisine,
                INGRIDIENTS to ingridients
            )
        }
    }
}