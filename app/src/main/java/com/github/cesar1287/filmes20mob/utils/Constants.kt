package com.github.cesar1287.filmes20mob.utils

class Constants {
    object Api {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_W154_IMAGE = "https://image.tmdb.org/t/p/w154"
        const val BASE_URL_W185_IMAGE = "https://image.tmdb.org/t/p/w185"
        const val BASE_URL_W500_IMAGE = "https://image.tmdb.org/t/p/w500"
        const val API_TOKEN = "3fdab48e2bddf5d597050debe64abb1c"
        const val API_TOKEN_KEY = "api_key"
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_LANGUAGE_VALUE = "pt-BR"
        const val QUERY_PARAM_REGION_LABEL = "region"
        const val QUERY_PARAM_REGION_VALUE = "US"
    }

    object Intent {
        const val KEY_INTENT_MOVIE = "movie"
    }

    object Paging {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

    object Firebase {
        const val FIRESTORE_COLLECTION_MOVIES = "movies"
    }
}