package com.github.cesar1287.filmes20mob.ui.favorite.data

import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.ResponseApi

interface FavoriteRepository {

    suspend fun getFavoriteMoviesFromUser(): ResponseApi

    suspend fun removeMovieFromFavorites(favoriteRemoved: MovieItem): ResponseApi
}