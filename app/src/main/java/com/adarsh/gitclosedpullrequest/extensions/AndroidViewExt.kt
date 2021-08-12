package com.adarsh.gitclosedpullrequest.extensions

import android.view.View
import android.widget.TextView

fun TextView.showWhenDataIsAvailable(
        textToShow: String?,
        default: String? = null
) {
    when {
        textToShow != null -> {
            visibility = View.VISIBLE
            text = textToShow
        }
        default.isNotNullAndNotEmpty() -> {
            visibility = View.VISIBLE
            text = default
        }
        else -> {
            visibility = View.GONE
        }
    }
}

fun View.setVisibility(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}