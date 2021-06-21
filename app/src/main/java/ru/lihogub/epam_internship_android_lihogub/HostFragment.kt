package ru.lihogub.epam_internship_android_lihogub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HostFragment: Fragment(R.layout.fragment_host) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHostContainer, MealListFragment.newInstance())
            .commit()
    }
    companion object {
        fun newInstance() = HostFragment()
    }
}