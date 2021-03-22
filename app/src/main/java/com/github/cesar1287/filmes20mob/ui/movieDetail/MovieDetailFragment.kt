package com.github.cesar1287.filmes20mob.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.CmpMediaDetailsBottomPosterCardBinding
import com.github.cesar1287.filmes20mob.databinding.FragmentMovieDetailBinding
import com.github.cesar1287.filmes20mob.extensions.shareMovie
import com.github.cesar1287.filmes20mob.extensions.showNearbyTheaters
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.Command
import com.github.cesar1287.filmes20mob.utils.Constants.Intent.KEY_INTENT_MOVIE
import com.github.cesar1287.filmes20mob.utils.GlideApp
import com.github.cesar1287.filmes20mob.utils.RegisterEventsAnalytics

class MovieDetailFragment : BaseFragment() {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return movieDetailBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.getParcelable<MovieItem>(KEY_INTENT_MOVIE)

        movieDetailBinding?.let {
            with(it) {
                setupHeader(movie, it.cmpMovieDetailsBottomCard)

                btMovieDetailsBackIcon.setOnClickListener {
                    activity?.onBackPressed()
                }

                activity?.let { activityNonNull ->
                    GlideApp.with(activityNonNull).load(movie?.backdropPath)
                        .into(ivMovieDetailsPosterImage)
                }

                tvMovieDetailsDescriptionText.text = movie?.overview

                btMovieDetailsShareButton.setOnClickListener {
                    RegisterEventsAnalytics.registerEvent(getString(R.string.movie_detail_share))
                    movie?.let { activity?.shareMovie(movie) }
                }

                btMovieDetailNearbyTheaters.setOnClickListener {
                    RegisterEventsAnalytics.registerEvent(getString(R.string.movie_detail_nearby_movie_theaters))
                    activity?.showNearbyTheaters()
                }
            }
        }
    }

    private fun setupHeader(
        movieItem: MovieItem?,
        cmpMovieDetailsBottomCard: CmpMediaDetailsBottomPosterCardBinding
    ) {
        with(cmpMovieDetailsBottomCard) {
            tvCmpMediaDetailsTitle.text = movieItem?.title
            tvCmpMediaDetailsGenres.text = movieItem?.genres
            tvCmpMediaDetailsYear.text = movieItem?.releaseDate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieDetailBinding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}