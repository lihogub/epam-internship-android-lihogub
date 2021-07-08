package ru.lihogub.epam_internship_android_lihogub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MealDetailsTagAdapter : RecyclerView.Adapter<MealDetailsTagHolder>() {
    var tagList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailsTagHolder {
        val listItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tag_item, parent, false)
        return MealDetailsTagHolder(listItem)
    }

    override fun onBindViewHolder(holder: MealDetailsTagHolder, position: Int) {
        holder.bind(tagList[position])
    }

    override fun getItemCount(): Int = tagList.size

}