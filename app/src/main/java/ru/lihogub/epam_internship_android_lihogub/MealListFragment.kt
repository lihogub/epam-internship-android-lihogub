package ru.lihogub.epam_internship_android_lihogub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MealListFragment : Fragment(R.layout.fragment_meal_list) {
    val prefs: SharedPreferences? by lazy { context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    var mealCategoryAdapter: MealCategoryAdapter? = null
    var mealListAdapter: MealListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val mealListRecyclerView = view?.findViewById<RecyclerView>(R.id.rv)
        val categoryListRecyclerView = view?.findViewById<RecyclerView>(R.id.category_rv)

        mealListAdapter = MealListAdapter(
            object : OnItemClickListener {
                override fun onClick(mealListItem: MealListItem) = openMealDetailsFragment(mealListItem)
            }
        )
        mealListRecyclerView?.adapter = mealListAdapter
        mealListRecyclerView?.layoutManager = LinearLayoutManager(context)

        mealCategoryAdapter = MealCategoryAdapter()
        mealCategoryAdapter?.onCategoryClickListener = object : OnCategoryClickListener {
            override fun onClick(category: Category) {
                openCategory(category)
                mealCategoryAdapter?.setPosition(category.id)
            }
        }
        categoryListRecyclerView?.adapter = mealCategoryAdapter
        categoryListRecyclerView?.layoutManager = LinearLayoutManager(context)
            .apply { orientation     = LinearLayoutManager.HORIZONTAL }

        val lastCategoryId = prefs?.getInt("last_category_id", 0) ?: 0
        loadCategories(lastCategoryId)
    }

    private fun loadCategories(lastCategoryId: Int) =
        Api.mealApi.getCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categoryList ->
                mealCategoryAdapter?.categoryList = categoryList.categories
                mealCategoryAdapter?.setPosition(lastCategoryId)
                val lastCategoryIndex = lastCategoryId - 1
                val lastCategory = categoryList.categories[lastCategoryIndex]
                openCategory(lastCategory)
            },{
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
            })

    private fun openCategory(category: Category) =
        Api.mealApi.getMealList(category.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mealList ->
                prefs
                    ?.edit()
                    ?.putInt("last_category_id", category.id)
                    ?.apply()
                mealListAdapter?.list = mealList.meals
                mealListAdapter?.notifyDataSetChanged()
            },{
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
            })

    private fun openMealDetailsFragment(mealListItem: MealListItem) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentHostContainer,
                MealDetailsFragment.newInstance(mealListItem.id)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mealCategoryAdapter = null
        mealListAdapter = null
    }

    companion object {
        fun newInstance() = MealListFragment()
    }
}