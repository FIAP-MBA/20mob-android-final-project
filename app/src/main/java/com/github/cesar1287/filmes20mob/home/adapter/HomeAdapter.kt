package com.github.cesar1287.filmes20mob.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.databinding.WatchCardItemBinding
import com.github.cesar1287.filmes20mob.model.MovieItem

class HomeAdapter(
    private val onItemClicked: (MovieItem?) -> Unit
) : PagedListAdapter<MovieItem, HomeAdapter.ViewHolder>(MovieItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WatchCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class ViewHolder(
        private val binding: WatchCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: MovieItem?,
            onItemClicked: (MovieItem?) -> Unit
        ) = with(binding) {
            movie?.let {
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .placeholder(R.drawable.app_logo_512)
                    .into(ivWatchImage)
                tvWatchTitle.text = movie.title
                tvWatchYear.text = movie.year
                tvWatchGenre.text = movie.genres
                cvWatch.setOnClickListener {
                    onItemClicked(movie)
                }
//                btWatchShare.setOnClickListener {
//                    val intent = Intent()
//                    intent.action = Intent.ACTION_SEND
//                    intent.putExtra(
//                        Intent.EXTRA_TEXT,
//                        itemView.context.getString(R.string.share_movie_message, movie.title)
//                    )
//                    intent.type = "text/plain"
//                    itemView.context.startActivity(intent)
//                }
            }
        }
    }
}