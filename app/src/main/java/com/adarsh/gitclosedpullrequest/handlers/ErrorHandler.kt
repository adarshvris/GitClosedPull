package com.adarsh.gitclosedpullrequest.handlers

import android.content.res.Resources
import com.adarsh.gitclosedpullrequest.base.network.ApiException
import com.example.gitclosedpullrequest.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

object ErrorHandler {

    fun handleError(throwable: Throwable, resources: Resources): String {
        return when (throwable) {
            is ConnectException -> {
                resources.getString(R.string.unable_to_connect)
            }
            is UnknownHostException -> {
                resources.getString(R.string.no_network_connection)
            }
            is HttpException -> {
                throwable.message ?: resources.getString(R.string.no_network_connection)
            }
            is ApiException -> {
                // Special case as we have restriction for accessing github api. After reaching maximum
                // limit, github starts sending 403 error code
                if (throwable.responseCode == 403) {
                    resources.getString(R.string.reached_maximum_hits)
                } else {
                    throwable.message ?: resources.getString(R.string.no_network_connection)
                }
            }
            else -> {
                resources.getString(R.string.no_network_connection)
            }
        }
    }
}