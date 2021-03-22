package com.github.cesar1287.filmes20mob.utils

import java.util.*

class Constants {
    object Api {
        const val API_TOKEN = "3fdab48e2bddf5d597050debe64abb1c"
        const val API_TOKEN_KEY = "api_key"
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_REGION_LABEL = "region"
        const val QUERY_PARAM_REGION_VALUE = "US"

        fun queryParamLanguageValue(): String {
            return if (Locale.getDefault().isO3Country == "BRA") "pt-BR"
            else "en-US"
        }
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
        const val KEY_REMOTE_CONFIG_APP_NAME = "experiment_app_name"
        const val CHANNEL_ID = "Novidades"
    }
}