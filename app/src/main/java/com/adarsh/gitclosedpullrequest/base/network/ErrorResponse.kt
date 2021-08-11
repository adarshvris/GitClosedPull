package com.adarsh.gitclosedpullrequest.base.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(
    val imageUrl: String?,
    val subtitle: String?,
    val title: String?,
    val type: String?
) : Parcelable