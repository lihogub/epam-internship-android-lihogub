package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment(R.layout.fragment_meal_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        val mealListRecyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val categoryListRecyclerView = view.findViewById<RecyclerView>(R.id.category_rv)

        val mealListAdapter = MealListAdapter(
            object : OnItemClickListener {
                override fun onClick(mealListItem: MealListItem) = openMealDetailsFragment(mealListItem)
            }
        )
        mealListRecyclerView.adapter = mealListAdapter
        mealListRecyclerView.layoutManager = LinearLayoutManager(context)

        val mealCategoryAdapter = MealCategoryAdapter()
        mealCategoryAdapter.onCategoryClickListener = object : OnCategoryClickListener {
            override fun onClick(category: Category) {
                openCategory(mealListAdapter, category)
                mealCategoryAdapter.setPosition(category.id)
            }
        }
        categoryListRecyclerView.adapter = mealCategoryAdapter
        categoryListRecyclerView.layoutManager = LinearLayoutManager(context)
            .apply { orientation     = LinearLayoutManager.HORIZONTAL }

        loadCategories(mealCategoryAdapter)
    }

    private fun loadCategories(categoryAdapter: MealCategoryAdapter) {
        Api.mealApi.getCategoryList().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                categoryAdapter.categoryList = response.body()?.categories ?: listOf()
                categoryAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                categoryAdapter.categoryList = listOf()
            }
        })
    }

    private fun openCategory(mealListAdapter: MealListAdapter, category: Category) {
        Api.mealApi.getMealList(category.name).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                mealListAdapter.list = response.body()?.meals ?: listOf()
                mealListAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                mealListAdapter.list = listOf()
            }
        })
    }

    private fun openMealDetailsFragment(mealListItem: MealListItem) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentHostContainer,
                MealDetailsFragment.newInstance(mealListItem.id)
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = MealListFragment()
    }
}