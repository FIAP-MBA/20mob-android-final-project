package com.github.cesar1287.filmes20mob.home.domain

import com.github.cesar1287.filmes20mob.extensions.getPosterImagePath
import com.github.cesar1287.filmes20mob.extensions.getYear
import com.github.cesar1287.filmes20mob.home.data.HomeRepository
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.model.MoviesResults
import com.github.cesar1287.filmes20mob.utils.GenresCache
import com.github.cesar1287.filmes20mob.utils.ResponseApi

class HomeUseCase(
    private val homeRepository: HomeRepository
) {

    fun setupMoviesList(list: MoviesResults?): List<MovieItem> {
        val movies = list?.results
        val movieGenres = GenresCache.genres.genres?.map { it.id to it.name }?.toMap()

        movies?.forEach { movie ->
            movie.posterPath = movie.posterPath?.getPosterImagePath()
            movie.releaseDate = movie.releaseDate?.getYear()
            movieGenres?.let {
                val genres = movie.genreIds.map { it to movieGenres[it] }.toMap()
                movie.genres = genres.values.joinToString()
            }
        }
        return movies ?: listOf()
    }

    suspend fun loadGenres(): ResponseApi {
        return homeRepository.getGenres()
    }
}