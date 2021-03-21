package com.github.cesar1287.filmes20mob.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.FragmentFavoriteBinding
import com.github.cesar1287.filmes20mob.utils.Command

class FavoriteFragment : BaseFragment() {

    override var command: MutableLiveData<Command> = MutableLiveData()
    private var favoriteBinding: FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return favoriteBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteBinding = null
    }
}