package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.databinding.FragmentMealListBinding
import ru.lihogub.epam_internship_android_lihogub.getAppComponent
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModel
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.SortingRule
import javax.inject.Inject


class MealListFragment : Fragment(), OnCategoryClickListener, OnMealClickListener, OnMealLikeClickListener {

    private lateinit var binding: FragmentMealListBinding

    @Inject
    lateinit var mealListViewModel: MealListViewModel

    private var mealAdapter: MealAdapter? = MealAdapter(this, this)
    private var categoryAdapter: CategoryAdapter? = CategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().getAppComponent().inject(this)
        initView()
        mealListViewModel.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mealAdapter = null
        categoryAdapter = null
    }

    override fun onCategoryClick(categoryName: String) {
        mealListViewModel.openCategory(categoryName)
    }

    override fun onMealClick(mealId: Int) {
        findNavController().navigate(
            MealListFragmentDirections.openMealDetailsFragmentAction(mealId)
        )
    }

    override fun onMealLikeClick(mealId: Int) {
        mealListViewModel.toggleLike(mealId)
    }

    private fun initView() {
        initRecyclers()
        subscribeAdapters()
        setupFilterBottomSheet()
    }

    private fun initRecyclers() {
        binding.rvMealList.adapter = mealAdapter
        binding.rvCategoryList.adapter = categoryAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeAdapters() {
        mealListViewModel.mealList.observe(viewLifecycleOwner) {
            mealAdapter?.mealList = it
            mealAdapter?.notifyDataSetChanged()
        }

        mealListViewModel.categoryList.observe(viewLifecycleOwner) {
            categoryAdapter?.setCategoryList(it)
        }

        mealListViewModel.currentCategory.observe(viewLifecycleOwner) {
            categoryAdapter?.setCurrentCategoryName(it)
        }
    }

    private fun setupFilterBottomSheet() {
        with(requireActivity()) {
            val bottomSheetBehavior = BottomSheetBehavior.from(
                findViewById(R.id.filterBottomSheet)
            )

            binding.toolbarOptionsButton.setOnClickListener {
                bottomSheetBehavior.state = STATE_EXPANDED
            }

            findViewById<Button>(R.id.closeButton).setOnClickListener {
                bottomSheetBehavior.state = STATE_COLLAPSED
            }

            findViewById<Button>(R.id.ascButton).let {
                it.setOnClickListener { mealListViewModel.sortMealsByAscending() }
                subscribeButtonToLiveData(it, mealListViewModel.sortingRule, SortingRule.ASC)
            }

            findViewById<Button>(R.id.descButton).let {
                it.setOnClickListener { mealListViewModel.sortMealsByDescending() }
                subscribeButtonToLiveData(it, mealListViewModel.sortingRule, SortingRule.DESC)
            }

            findViewById<Button>(R.id.applyButton).setOnClickListener {
                mealListViewModel.applySort()
                bottomSheetBehavior.state = STATE_COLLAPSED
            }
        }
    }

    private fun <T> subscribeButtonToLiveData(
        button: Button,
        liveData: LiveData<T>,
        targetValue: T
    ) {
        liveData.observe(viewLifecycleOwner) { currentValue ->
            val bgColorId = if (currentValue == targetValue) R.color.pink else R.color.white_darker
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), bgColorId))
        }
    }
}
