package com.github.cesar1287.filmes20mob.ui.favorite.domain

import com.github.cesar1287.filmes20mob.ui.favorite.data.FavoriteRepository
import com.github.cesar1287.filmes20mob.utils.ResponseApi

class FavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {

    suspend fun getFavoriteMoviesFromUser(): ResponseApi {
        return favoriteRepository.getFavoriteMoviesFromUser()
    }
}