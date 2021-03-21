package com.github.cesar1287.filmes20mob.ui.home.data

import android.content.Context
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.api.ApiService
import com.github.cesar1287.filmes20mob.base.BaseRepository
import com.github.cesar1287.filmes20mob.model.Genre
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.model.MoviesResults
import com.github.cesar1287.filmes20mob.utils.Constants.Firebase.FIRESTORE_COLLECTION_MOVIES
import com.github.cesar1287.filmes20mob.utils.ResponseApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HomeRepositoryImpl(
    private var context: Context
) : HomeRepository, BaseRepository(context) {

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

    override suspend fun saveMovie(movie: MovieItem): ResponseApi {
        return try {
            Firebase.firestore.collection(
                FIRESTORE_COLLECTION_MOVIES
            ).document(movie.id.toString()).set(movie, SetOptions.merge()).await()

            Firebase.auth.uid?.let {
                val movieRef = Firebase.firestore.collection(FIRESTORE_COLLECTION_MOVIES)
                    .document(movie.id.toString())
                movieRef.update("uid", FieldValue.arrayUnion(it)).await()
            }

            ResponseApi.Success(true)
        } catch (exception: Exception) {
            ResponseApi.Error(context.getString(R.string.error_home_add_favorite))
        }
    }
}