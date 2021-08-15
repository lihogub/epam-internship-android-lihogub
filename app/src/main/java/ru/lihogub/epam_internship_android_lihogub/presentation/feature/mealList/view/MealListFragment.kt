package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.databinding.FragmentMealListBinding
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetCategoryListUseCase
import ru.lihogub.epam_internship_android_lihogub.domain.useCase.GetMealListUseCase
import ru.lihogub.epam_internship_android_lihogub.getAppComponent
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModel
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.MealListViewModelFactory
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealList.viewModel.SortingRule
import javax.inject.Inject


class MealListFragment : Fragment(), OnCategoryClickListener,
    OnMealClickListener {
    private lateinit var binding: FragmentMealListBinding

    @Inject
    lateinit var getMealListUseCase: GetMealListUseCase

    @Inject
    lateinit var getCategoryListUseCase: GetCategoryListUseCase

    private val mealListViewModel by viewModels<MealListViewModel> {
        MealListViewModelFactory(
            myApplication = requireActivity().application,
            getMealListUseCase = getMealListUseCase,
            getCategoryListUseCase = getCategoryListUseCase
        )
    }

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
        mealListViewModel.start()
        initView()
    }

    override fun onCategoryClick(categoryName: String) {
        mealListViewModel.openCategory(categoryName)
    }

    override fun onMealClick(mealId: Int) {
        findNavController().navigate(
            MealListFragmentDirections.openMealDetailsFragmentAction(mealId)
        )
    }

    private fun initView() {
        initCategoryListRecyclerView()
        initMealListRecyclerView()
        setupFilterBottomSheet()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMealListRecyclerView() {
        binding.rvMealList.adapter = MealAdapter(this).apply {
            mealListViewModel.mealList.observe(viewLifecycleOwner) { newMealList ->
                mealList = newMealList
                notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initCategoryListRecyclerView() {
        binding.rvCategoryList.adapter = CategoryAdapter(this).apply {
            mealListViewModel.categoryList.observe(viewLifecycleOwner) { newCategoryList ->
                categoryList = newCategoryList
                notifyDataSetChanged()
            }
            mealListViewModel.currentCategory.observe(viewLifecycleOwner) { newCategoryName ->
                currentCategoryName = newCategoryName
                notifyDataSetChanged()
            }
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

    companion object
}
