package com.github.cesar1287.filmes20mob.api

import com.github.cesar1287.filmes20mob.BuildConfig
import com.github.cesar1287.filmes20mob.utils.Constants.Api.API_TOKEN
import com.github.cesar1287.filmes20mob.utils.Constants.Api.API_TOKEN_KEY
import com.github.cesar1287.filmes20mob.utils.Constants.Api.QUERY_PARAM_LANGUAGE_LABEL
import com.github.cesar1287.filmes20mob.utils.Constants.Api.QUERY_PARAM_LANGUAGE_VALUE
import com.github.cesar1287.filmes20mob.utils.Constants.Api.QUERY_PARAM_REGION_LABEL
import com.github.cesar1287.filmes20mob.utils.Constants.Api.QUERY_PARAM_REGION_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    val tmdbApi : TMDBApi = getTMDBApiClient().create(TMDBApi::class.java)

    fun getTMDBApiClient() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(API_TOKEN_KEY, API_TOKEN)
                    .addQueryParameter(QUERY_PARAM_LANGUAGE_LABEL, QUERY_PARAM_LANGUAGE_VALUE)
                    .addQueryParameter(QUERY_PARAM_REGION_LABEL, QUERY_PARAM_REGION_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }
}