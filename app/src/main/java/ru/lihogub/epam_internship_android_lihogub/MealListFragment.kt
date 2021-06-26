package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MealListFragment : Fragment(R.layout.fragment_meal_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val mealListAdapter = MealListAdapter(
            object : OnItemClickListener {
                override fun onClick(dish: Dish) = openMealDetailsFragment(dish)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mealListAdapter
        mealListAdapter.list.addAll(
            listOf(
                Api.getRecipeById(52992),
                Api.getRecipeById(52935)
            )
        )

        var lastActiveCategory = 0
        val categoryList = listOf(
            Category(0, true, R.drawable.beef),
            Category(1, false, R.drawable.dessert),
            Category(2, false, R.drawable.pasta),
            Category(3, false, R.drawable.miscellaneous),
            Category(4, false, R.drawable.lamb),
            Category(5, false, R.drawable.chicken)
        )

        val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_rv)
        val categoryAdapter = MealCategoryAdapter(0, categoryList)
        categoryRecyclerView.layoutManager = LinearLayoutManager(context)
            .apply { orientation = LinearLayoutManager.HORIZONTAL }
        categoryRecyclerView.adapter = categoryAdapter
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