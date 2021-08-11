package com.adarsh.gitclosedpullrequest.base.network

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseApiSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) {
                    Result.success(body)
                } else {
                    Result.error(Throwable("Backend response is null"))
                }
            }
            return error(ApiException(response.code(), response.errorBody()?.deserialize(), response.raw().request().url().toString()))
        } catch (e: Exception) {
            e.printStackTrace()
            return error(e)
        }
    }

    private fun <T> error(error: Throwable): Result<T> {
        return Result.error(error)
    }

}

class ApiException(val responseCode: Int, val errorResponse: ErrorResponse?, val failedUrl: String) : Exception()

inline fun <reified A> ResponseBody.deserialize(): A {

    val builder = GsonBuilder().setPrettyPrinting().serializeNulls()
    val gson = builder.create()
    return gson.fromJson(this.string(), A::class.java)

}