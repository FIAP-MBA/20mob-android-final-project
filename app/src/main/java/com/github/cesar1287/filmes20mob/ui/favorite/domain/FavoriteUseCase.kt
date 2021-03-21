package com.github.cesar1287.filmes20mob.ui.favorite

import com.github.cesar1287.filmes20mob.utils.ResponseApi

class FavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {

    suspend fun getFavoriteMoviesFromUser(): ResponseApi {
        TODO("Not yet implemented")
    }
}