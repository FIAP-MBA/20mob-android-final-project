package com.github.cesar1287.filmes20mob.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.databinding.WatchCardItemBinding
import com.github.cesar1287.filmes20mob.extensions.shareMovie
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.GlideApp

class FavoriteAdapter(
    private val moviesList: List<MovieItem>,
    private val onItemClicked: (MovieItem?) -> Unit,
    private val onFavoriteClick: (MovieItem?) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WatchCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position], onItemClicked, onFavoriteClick)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class ViewHolder(
        private val binding: WatchCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: MovieItem?,
            onItemClicked: (MovieItem?) -> Unit,
            onFavoriteClick: (MovieItem?) -> Unit
        ) = with(binding) {
            btFavoriteMovie.setImageResource(R.drawable.ic_favorite_24px)

            movie?.let {
                GlideApp.with(itemView.context)
                    .load(movie.posterPath)
                    .placeholder(R.drawable.app_logo_512)
                    .into(ivWatchImage)
                tvWatchTitle.text = movie.title
                tvWatchYear.text = movie.year
                tvWatchGenre.text = movie.genres
                cvWatch.setOnClickListener {
                    onItemClicked(movie)
                }
                btWatchShare.setOnClickListener {
                    itemView.context.shareMovie(movie)
                }
                btFavoriteMovie.setOnClickListener {
                    onFavoriteClick(movie)
                }
            }
        }
    }
}