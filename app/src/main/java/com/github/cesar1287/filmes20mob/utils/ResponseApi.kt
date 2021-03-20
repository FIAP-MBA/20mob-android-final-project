package com.github.cesar1287.filmes20mob.utils

sealed class ResponseApi {
    class Success(var data: Any?) : ResponseApi()
    class Error(val message: String) : ResponseApi()
}