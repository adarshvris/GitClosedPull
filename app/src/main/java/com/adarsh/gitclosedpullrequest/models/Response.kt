package com.adarsh.gitclosedpullrequest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ClosedPullData(
        var id: String? = UUID.randomUUID().toString(),
        val title: String?,
        @SerializedName("user")
        val userData: UserData?,
        @SerializedName("created_at")
        val createdTimeStamp: String?,
        @SerializedName("closed_at")
        val closedTimeStamp: String?
) : Serializable

data class UserData(
        val login: String?,
        @SerializedName("avatar_url")
        val userAvatarUrl: String?
) : Serializable