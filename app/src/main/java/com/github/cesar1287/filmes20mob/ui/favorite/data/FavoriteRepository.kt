package com.github.cesar1287.filmes20mob.ui.favorite.data

import com.github.cesar1287.filmes20mob.utils.ResponseApi

interface FavoriteRepository {

    suspend fun getFavoriteMoviesFromUser(): ResponseApi
}