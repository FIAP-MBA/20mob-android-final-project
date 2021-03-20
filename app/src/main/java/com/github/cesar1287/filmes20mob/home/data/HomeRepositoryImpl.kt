package com.github.cesar1287.filmes20mob.home.data

import com.github.cesar1287.filmes20mob.api.ApiService
import com.github.cesar1287.filmes20mob.base.BaseRepository
import com.github.cesar1287.filmes20mob.model.Genre
import com.github.cesar1287.filmes20mob.model.MoviesResults
import com.github.cesar1287.filmes20mob.utils.ResponseApi

class HomeRepositoryImpl : HomeRepository, BaseRepository() {

    override suspend fun getNowPlayingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies(page)
        }.let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    response.data = response.data as? MoviesResults
                    return@let response
                }
                is ResponseApi.Error -> return@let response
            }
        }
    }

    override suspend fun getGenres(): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getGenres()
        }.let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    response.data = response.data as? Genre
                    return@let response
                }
                is ResponseApi.Error -> return@let response
            }
        }
    }
}