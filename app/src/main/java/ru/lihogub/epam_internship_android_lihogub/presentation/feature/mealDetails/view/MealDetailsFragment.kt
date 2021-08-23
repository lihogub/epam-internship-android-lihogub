package ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import ru.lihogub.epam_internship_android_lihogub.R
import ru.lihogub.epam_internship_android_lihogub.databinding.FragmentMealDetailsBinding
import ru.lihogub.epam_internship_android_lihogub.getAppComponent
import ru.lihogub.epam_internship_android_lihogub.presentation.feature.mealDetails.viewModel.MealDetailsViewModel
import javax.inject.Inject


class MealDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailsBinding
    private val args by navArgs<MealDetailsFragmentArgs>()

    @Inject
    lateinit var mealDetailsViewModel: MealDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().getAppComponent().inject(this)
        initView()
        mealDetailsViewModel.start(args.mealId)
    }

    private fun initView() {
        mealDetailsViewModel.mealDetails.observe(viewLifecycleOwner) {
            with(requireActivity()) {
                findViewById<TextView>(R.id.name).text = it.name
                findViewById<TextView>(R.id.cuisine).text = it.area
                findViewById<TextView>(R.id.ingridients).text = it.ingredients
                findViewById<RecyclerView>(R.id.rvTagList).adapter =
                    MealDetailsTagAdapter(it.tagList)

                Glide.with(requireView().context)
                    .load(it.thumbUrl)
                    .into(findViewById(R.id.mealDetailsImageTop))

                findViewById<YouTubePlayerView>(R.id.youtubePlayer)
                    .addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            val videoId = it.youtubeUrl.substringAfterLast("=")
                            youTubePlayer.loadVideo(videoId, 0F);
                            youTubePlayer.pause()
                        }
                    })
            }
        }
    }
}
