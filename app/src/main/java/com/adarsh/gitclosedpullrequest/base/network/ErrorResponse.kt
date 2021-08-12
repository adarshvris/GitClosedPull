package com.adarsh.gitclosedpullrequest.base.network

import android.os.Parcelable
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize
import okhttp3.ResponseBody

@Parcelize
data class ErrorResponse(
        val imageUrl: String?,
        val subtitle: String?,
        val title: String?,
        val type: String?,
        val message: String?
) : Parcelable

class ApiException(val responseCode: Int, val errorResponse: ErrorResponse?, val failedUrl: String) : Exception()

inline fun <reified A> ResponseBody.deserialize(): A {
    val builder = GsonBuilder().setPrettyPrinting().serializeNulls()
    val gson = builder.create()
    return gson.fromJson(this.string(), A::class.java)
}