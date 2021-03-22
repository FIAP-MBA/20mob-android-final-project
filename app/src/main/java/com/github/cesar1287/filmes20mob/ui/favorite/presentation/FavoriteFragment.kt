package com.github.cesar1287.filmes20mob.ui.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.FragmentFavoriteBinding
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.ui.favorite.adapter.FavoriteAdapter
import com.github.cesar1287.filmes20mob.utils.Command
import com.github.cesar1287.filmes20mob.utils.Constants
import com.github.cesar1287.filmes20mob.utils.RegisterEventsAnalytics
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment() {

    override var command: MutableLiveData<Command> = MutableLiveData()
    private var favoriteBinding: FragmentFavoriteBinding? = null

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var moviesList: MutableList<MovieItem> = mutableListOf()

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
                moviesList = it.toMutableList()
                setupRecyclerView()
            } else {
                setupContentVisibility(visible = false)
            }
        })

        favoriteViewModel.onFavoriteMovieRemoved.observe(viewLifecycleOwner, { movieItem ->
            favoriteBinding?.rvHomeMoviesList?.let { recyclerView ->
                movieItem?.let { movieItemNonNull ->
                    val index = moviesList.indexOf(movieItemNonNull)
                    moviesList.removeAt(index)
                    recyclerView.adapter?.notifyItemRemoved(index)
                    if (moviesList.isEmpty()) {
                        setupContentVisibility(visible = false)
                    }
                }
                Snackbar.make(
                    recyclerView,
                    getString(R.string.movie_removed_from_favorites_successfully),
                    Snackbar.LENGTH_LONG
                ).show()
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

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        favoriteBinding?.rvHomeMoviesList?.layoutManager = layoutManager
        val adapter = FavoriteAdapter(moviesList, { movieClicked ->
            RegisterEventsAnalytics.registerEvent(getString(R.string.favorite_more_info))
            movieClicked?.let {
                val args = Bundle()
                args.putParcelable(Constants.Intent.KEY_INTENT_MOVIE, it)
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_movieDetailFragment,
                    args
                )
            }
        }, { favoriteRemoved ->
            RegisterEventsAnalytics.registerEvent(getString(R.string.favorite_favorite))
            favoriteRemoved?.let { favoriteViewModel.removeMovieFromFavorites(it) }
        })
        favoriteBinding?.rvHomeMoviesList?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteBinding = null
    }
}