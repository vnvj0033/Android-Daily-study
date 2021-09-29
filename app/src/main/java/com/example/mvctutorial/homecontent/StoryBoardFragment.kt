package com.example.mvctutorial.homecontent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mvctutorial.R
import com.example.mvctutorial.databinding.FragmentStoryBoardBinding
import com.example.mvctutorial.network.Post
import com.example.mvctutorial.network.RetrofitServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryBoardFragment : Fragment() {
    private lateinit var _binding: FragmentStoryBoardBinding
    val binding get() = _binding

    private val networkCallback = object : Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            val body = response.body()

            Log.d(javaClass.name, body.toString())
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStoryBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitServer.postAPI.getPost("1").enqueue(networkCallback)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val bannerHeight = arguments?.getInt("bannerHeight")
        bannerHeight?.let {
            binding.cuSectionBanner.layoutParams.height = bannerHeight
        }
    }
}
