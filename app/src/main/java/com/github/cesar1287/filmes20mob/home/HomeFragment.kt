package com.github.cesar1287.filmes20mob.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var homeBinding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }
}