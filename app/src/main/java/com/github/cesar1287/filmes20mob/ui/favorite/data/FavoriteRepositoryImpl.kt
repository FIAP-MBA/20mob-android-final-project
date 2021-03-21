package com.github.cesar1287.filmes20mob.ui.favorite.data

import android.content.Context
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseRepository
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.Constants
import com.github.cesar1287.filmes20mob.utils.ResponseApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FavoriteRepositoryImpl(
    private var context: Context
): FavoriteRepository, BaseRepository(context) {

    override suspend fun getFavoriteMoviesFromUser(): ResponseApi {
        return try {
            val moviesRef = Firebase.firestore.collection(
                Constants.Firebase.FIRESTORE_COLLECTION_MOVIES
            )
            val result = moviesRef
                .whereArrayContains(
                    "uid", Firebase.auth.currentUser?.uid ?: ""
                ).get().await()

            ResponseApi.Success(result.toObjects<MovieItem>())
        } catch (exception: Exception) {
            ResponseApi.Error(context.getString(R.string.error_favorite_default))
        }
    }

    override suspend fun removeMovieFromFavorites(favoriteRemoved: MovieItem): ResponseApi {
        return try {
            Firebase.auth.uid?.let {
                val movieRef = Firebase.firestore.collection(Constants.Firebase.FIRESTORE_COLLECTION_MOVIES).document(favoriteRemoved.id.toString())
                movieRef.update("uid", FieldValue.arrayRemove(it)).await()
            }

            ResponseApi.Success(true)
        } catch (exception: Exception) {
            ResponseApi.Error(context.getString(R.string.error_favorite_remove))
        }
    }
}