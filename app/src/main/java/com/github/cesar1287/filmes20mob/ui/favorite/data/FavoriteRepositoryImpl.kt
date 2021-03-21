package com.github.cesar1287.filmes20mob.ui.favorite.data

import com.github.cesar1287.filmes20mob.base.BaseRepository
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.Constants
import com.github.cesar1287.filmes20mob.utils.ResponseApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FavoriteRepositoryImpl: FavoriteRepository, BaseRepository() {

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
            ResponseApi.Error("Falha ao carregar os favoritos, tente novamente")
        }
    }
}