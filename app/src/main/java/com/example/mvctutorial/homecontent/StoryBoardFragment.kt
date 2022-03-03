package com.example.mvctutorial.homecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvctutorial.databinding.FragmentStoryBoardBinding

class StoryBoardFragment : Fragment() {
    private var _binding: FragmentStoryBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStoryBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val bannerHeight = arguments?.getInt("bannerHeight")
        bannerHeight?.let {
            binding.cuSectionBanner.layoutParams.height = bannerHeight
        }
    }
}
