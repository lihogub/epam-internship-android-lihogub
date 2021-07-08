package ru.lihogub.epam_internship_android_lihogub

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tagRecyclerView = view.findViewById<RecyclerView>(R.id.tag_rv)
        tagRecyclerView.layoutManager = LinearLayoutManager(context)
            .apply{ orientation = LinearLayoutManager.HORIZONTAL }

        val tagAdapter = MealDetailsTagAdapter()
        tagRecyclerView.adapter = tagAdapter

        Api.mealApi.getMealDetailsList(arguments?.getInt(ID) ?: 0).enqueue(object : Callback<MealDetailsList> {
            override fun onResponse(call: Call<MealDetailsList>, response: Response<MealDetailsList>) {
                val mealDetailsUIModel = response.body()?.meals?.first()?.toMealDetailsUIModel()

                val cuisineTextView = view.findViewById<TextView>(R.id.cuisine)
                cuisineTextView.text = mealDetailsUIModel?.area?.uppercase()

                val nameTextView = view.findViewById<TextView>(R.id.name)
                nameTextView.text = mealDetailsUIModel?.name

                val ingridientsTextView = view.findViewById<TextView>(R.id.ingridients)
                ingridientsTextView.text = mealDetailsUIModel?.ingredients

                Glide.with(view.context)
                    .load(mealDetailsUIModel?.thumbUrl)
                    .into(view.findViewById(R.id.meal_details_image_top))
                Glide.with(view.context)
                    .load(mealDetailsUIModel?.thumbUrl)
                    .into(view.findViewById(R.id.meal_details_image_bottom))

                tagAdapter.tagList = mealDetailsUIModel?.tagList ?: listOf()
                tagAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MealDetailsList>, t: Throwable) {}
        })
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(mealListItem: MealListItem) = MealDetailsFragment().apply {
            arguments = bundleOf(
                ID to mealListItem.id
            )
        }
    }
}