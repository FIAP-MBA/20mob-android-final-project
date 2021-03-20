package com.github.cesar1287.filmes20mob.home.domain

import com.github.cesar1287.filmes20mob.extensions.getPosterImagePath
import com.github.cesar1287.filmes20mob.extensions.getYear
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.model.MoviesResults

class HomeUseCase {

    fun setupMoviesList(list: MoviesResults?): List<MovieItem> {
        val movies = list?.results
        movies?.forEach { movie ->
            movie.posterPath = movie.posterPath?.getPosterImagePath()
            movie.releaseDate = movie.releaseDate?.getYear()
        }
        return movies ?: listOf()
    }
}