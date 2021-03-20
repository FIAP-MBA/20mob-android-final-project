package com.github.cesar1287.filmes20mob.base

import com.github.cesar1287.filmes20mob.utils.ErrorUtils
import com.github.cesar1287.filmes20mob.utils.ResponseApi
import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = safeApiResult(call)

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : ResponseApi {
        try {
            val response = call.invoke()

            return if(response.isSuccessful) {
                ResponseApi.Success(response.body())
            } else {
                val error = ErrorUtils.parseError(response)

                error?.message?.let {  message ->
                    ResponseApi.Error(message)
                } ?: run {
                    ResponseApi.Error("Erro padrão")
                }
            }
        } catch (error : Exception) {
            return ResponseApi.Error("Erro: ${error.localizedMessage}, stack:\n ${error.cause}")
        }
    }
}