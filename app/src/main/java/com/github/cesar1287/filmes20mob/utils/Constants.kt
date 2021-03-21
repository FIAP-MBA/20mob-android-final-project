package com.github.cesar1287.filmes20mob.utils

import java.util.*

class Constants {
    object Api {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_W154_IMAGE = "https://image.tmdb.org/t/p/w154"
        const val BASE_URL_W185_IMAGE = "https://image.tmdb.org/t/p/w185"
        const val BASE_URL_W500_IMAGE = "https://image.tmdb.org/t/p/w500"
        const val BASE_URL_W780_IMAGE = "https://image.tmdb.org/t/p/w780"
        const val API_TOKEN = "3fdab48e2bddf5d597050debe64abb1c"
        const val API_TOKEN_KEY = "api_key"
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_REGION_LABEL = "region"
        const val QUERY_PARAM_REGION_VALUE = "US"
        const val KEY_MEDIA_TYPE_MOVIE = "movie"

        fun queryParamLanguageValue(): String {
            return if (Locale.getDefault().isO3Country == "BRA") "pt-BR"
            else "en-US"
        }
    }

    object Intent {
        const val KEY_INTENT_PERSON = "person"
        const val KEY_INTENT_MOVIE_ID = "movie_id"
        const val KEY_INTENT_TV_ID = "tv_id"
        const val KEY_INTENT_TRAILER_ID = "trailer_id"
        const val KEY_INTENT_USER = "user"
    }

    object Paging {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

    object Firebase {
        const val FIRESTORE_COLLECTION_USERS = "users"
        const val FIRESTORE_COLLECTION_MOVIES = "movies"
        const val FIRESTORE_LOG_KEY = "Firebase Firestore"
        const val STORAGE_LOG_KEY = "Firebase Storage"
    }
}