package com.github.cesar1287.filmes20mob.ui.home.data

import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.ResponseApi

interface HomeRepository {

    suspend fun getNowPlayingMovies(page: Int): ResponseApi

    suspend fun getGenres(): ResponseApi

    suspend fun saveMovie(movie: MovieItem): ResponseApi
}