package com.example.mvctutorial.navigate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mvctutorial.R
import com.example.mvctutorial.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.firstView.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.navigationToNext)
        }
    }
}