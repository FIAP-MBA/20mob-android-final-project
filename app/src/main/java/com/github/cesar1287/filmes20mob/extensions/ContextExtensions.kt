package com.github.cesar1287.filmes20mob.extensions

import android.content.Context
import android.content.Intent
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.model.MovieItem

fun Context.shareMovie(movie: MovieItem) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(
        Intent.EXTRA_TEXT,
        this.getString(
            R.string.share_movie_message,
            movie.title,
            movie.homepage ?: ""
        )
    )
    intent.type = "text/plain"
    this.startActivity(
        Intent.createChooser(
            intent,
            this.getString(R.string.share_using)
        )
    )
}