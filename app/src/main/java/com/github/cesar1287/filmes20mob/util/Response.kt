package com.github.cesar1287.filmes20mob.util

sealed class Response<out T> {
    class Success<T>(val data: T?) : Response<T>()
    class Error<T>(val message: String) : Response<T>()
    class Loading<T> : Response<T>()
}