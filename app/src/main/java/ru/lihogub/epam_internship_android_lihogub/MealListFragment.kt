package ru.lihogub.epam_internship_android_lihogub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import io.reactivex.Completable
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

        val lastCategoryId = prefs?.getInt("last_category_id", 1) ?: 1
        loadCategories(lastCategoryId)

        val bottomSheet: ConstraintLayout = requireActivity().findViewById(R.id.bottomSheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        requireActivity().findViewById<Button>(R.id.toolbarOptionsButton)
            .setOnClickListener{
                bottomSheetBehavior.state = STATE_EXPANDED
            }

        requireActivity().findViewById<Button>(R.id.asc_button)
            .setOnClickListener{
                val sortedMealList = mealListAdapter?.list?.sortedBy { it.name } ?: listOf()
                mealListAdapter?.list = sortedMealList
            }

        requireActivity().findViewById<Button>(R.id.desc_button)
            .setOnClickListener{
                val sortedMealList = mealListAdapter?.list?.sortedByDescending { it.name } ?: listOf()
                mealListAdapter?.list = sortedMealList
            }

        requireActivity().findViewById<Button>(R.id.applyButton)
            .setOnClickListener{
                bottomSheetBehavior.state = STATE_COLLAPSED
                mealListAdapter?.notifyDataSetChanged()
            }

        requireActivity().findViewById<Button>(R.id.toolbarCloseButton)
            .setOnClickListener{
                bottomSheetBehavior.state = STATE_COLLAPSED
            }
    }

    private fun loadCategories(lastCategoryId: Int) {
        fetchCategoriesFromDb({ categoryList ->
            Log.d(this::class.java.name, "Fetched categories from db")
            if (categoryList.isEmpty()) {
                Log.d(this::class.java.name, "Db is empty")
                fetchCategoriesFromApi({ fetchedFromApi ->
                    Log.d(this::class.java.name, "Fetched categories from API")
                    saveCategoriesToDb(fetchedFromApi)
                    Completable
                        .fromAction { setCategoriesAndOpenOne(fetchedFromApi, lastCategoryId) }
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }, {
                    Log.e(this::class.java.name, "Failed to fetch categories from API", it)
                })
            } else {
                Completable
                    .fromAction { setCategoriesAndOpenOne(categoryList, lastCategoryId) }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }, {
            Log.e(this::class.java.name, "Failed to fetch categories from db", it)
        })
    }

    private fun fetchCategoriesFromApi(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) =
        Api.mealApi.getCategoryList()
            .map { categoryListDto -> categoryListDto.categories }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categoryList -> onSuccess(categoryList) }, { onError(it) })

    private fun fetchCategoriesFromDb(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) =
        AppDatabase.getInstance(requireContext()).getCategoryDao().getCategoryList()
            .subscribeOn(Schedulers.io())
            .subscribe({ categoryList -> onSuccess(categoryList) }, { onError(it) })

    private fun saveCategoriesToDb(categoryList: List<Category>) =
        AppDatabase.getInstance(requireContext()).getCategoryDao().insertCategoryList(categoryList)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.d(this::class.java.name, "Inserted categories to db") },
                { Log.e(this::class.java.name, "Failed to insert categories to db") }
            )

    private fun setCategoriesAndOpenOne(categoryList: List<Category>, lastCategoryId: Int) {
        mealCategoryAdapter?.categoryList = categoryList
        mealCategoryAdapter?.setPosition(lastCategoryId)
        val lastCategoryIndex = lastCategoryId - 1
        val lastCategory = categoryList[lastCategoryIndex]
        openCategory(lastCategory)
    }

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