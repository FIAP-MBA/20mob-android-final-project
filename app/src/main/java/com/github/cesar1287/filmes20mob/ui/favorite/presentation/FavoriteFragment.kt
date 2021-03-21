package com.github.cesar1287.filmes20mob.ui.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.FragmentFavoriteBinding
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.ui.favorite.adapter.FavoriteAdapter
import com.github.cesar1287.filmes20mob.utils.Command
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment() {

    override var command: MutableLiveData<Command> = MutableLiveData()
    private var favoriteBinding: FragmentFavoriteBinding? = null

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return favoriteBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.command = command
        favoriteViewModel.getFavoriteMoviesFromUser()

        setupObservables()
    }

    private fun setupObservables() {
        favoriteViewModel.onFavoriteMoviesLoaded.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                setupContentVisibility(visible = true)
                setupRecyclerView(it)
            } else {
                setupContentVisibility(visible = false)
            }
        })
    }

    private fun setupContentVisibility(visible: Boolean) {
        favoriteBinding?.let {
            with(it) {
                vgFavoriteEmptyState.isVisible = !visible
                rvHomeMoviesList.isVisible = visible
            }
        }
    }

    private fun setupRecyclerView(movies: List<MovieItem>) {
        val layoutManager = LinearLayoutManager(requireContext())
        favoriteBinding?.rvHomeMoviesList?.layoutManager = layoutManager
        val creditCardAdapter = FavoriteAdapter(movies,  { movieClicked ->
            //todo
        }, { favoriteRemoved ->

        })
        favoriteBinding?.rvHomeMoviesList?.adapter = creditCardAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteBinding = null
    }
}