package ru.lihogub.epam_internship_android_lihogub

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealDetailsTagHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(tagText: String) {
        view.findViewById<TextView>(R.id.meal_details_tag).text = tagText
    }
}