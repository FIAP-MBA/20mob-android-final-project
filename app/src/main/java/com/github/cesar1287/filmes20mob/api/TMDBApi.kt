package com.github.cesar1287.filmes20mob.api

import com.github.cesar1287.filmes20mob.model.Genre
import com.github.cesar1287.filmes20mob.model.MoviesResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MoviesResults>

    @GET("genre/movie/list")
    suspend fun getGenres(
    ): Response<Genre>
}