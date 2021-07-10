package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {
    var tagAdapter: MealDetailsTagAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val tagRecyclerView = view?.findViewById<RecyclerView>(R.id.tag_rv)
        tagRecyclerView?.layoutManager = LinearLayoutManager(context)
            .apply{ orientation = LinearLayoutManager.HORIZONTAL }

        val tagAdapter = MealDetailsTagAdapter()
        tagRecyclerView?.adapter = tagAdapter

        loadMealDetails()
    }

    private fun loadMealDetails() =
        Api.mealApi.getMealDetailsList(arguments?.getInt(ID) ?: 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.meals.first().toMealDetailsUIModel()
            }
            .subscribe ({ mealDetailsUIModel ->

                val cuisineTextView = view?.findViewById<TextView>(R.id.cuisine)
                cuisineTextView?.text = mealDetailsUIModel?.area?.uppercase()

                val nameTextView = view?.findViewById<TextView>(R.id.name)
                nameTextView?.text = mealDetailsUIModel?.name

                val ingridientsTextView = view?.findViewById<TextView>(R.id.ingridients)
                ingridientsTextView?.text = mealDetailsUIModel?.ingredients

                Glide.with(requireView().context)
                    .load(mealDetailsUIModel?.thumbUrl)
                    .into(requireView().findViewById(R.id.meal_details_image_top))
                Glide.with(requireView().context)
                    .load(mealDetailsUIModel?.thumbUrl)
                    .into(requireView().findViewById(R.id.meal_details_image_bottom))

                tagAdapter?.tagList = mealDetailsUIModel.tagList
                tagAdapter?.notifyDataSetChanged()

            }, {
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
            })

    override fun onDestroyView() {
        super.onDestroyView()
        tagAdapter = null
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(id: Int) = MealDetailsFragment().apply {
            arguments = bundleOf(
                ID to id
            )
        }
    }
}