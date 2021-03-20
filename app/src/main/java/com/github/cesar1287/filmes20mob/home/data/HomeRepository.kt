package com.github.cesar1287.filmes20mob.home.data

import com.github.cesar1287.filmes20mob.utils.ResponseApi

interface HomeRepository {

    suspend fun getNowPlayingMovies(page: Int): ResponseApi

    suspend fun getGenres(): ResponseApi
}