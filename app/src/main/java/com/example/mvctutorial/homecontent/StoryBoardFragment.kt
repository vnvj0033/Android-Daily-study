package com.example.mvctutorial.homecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mvctutorial.R

class StoryBoardFragment : Fragment() {
    private lateinit var banner : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_story_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        banner = view.findViewById(R.id.cuSectionBanner)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val bannerHeight = arguments?.getInt("bannerHeight")
        bannerHeight?.let {
            banner.layoutParams.height = bannerHeight
        }
    }
}
