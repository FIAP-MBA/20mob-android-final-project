package com.github.cesar1287.filmes20mob.extensions

import com.github.cesar1287.filmes20mob.utils.Constants

fun String.getPosterImagePath(size: String = "small"): String {
    return when (size) {
        "small" -> "${Constants.Api.BASE_URL_W154_IMAGE}$this"
        "medium" -> "${Constants.Api.BASE_URL_W185_IMAGE}$this"
        "large" -> "${Constants.Api.BASE_URL_W342_IMAGE}$this"
        else -> "${Constants.Api.BASE_URL_W154_IMAGE}$this"
    }
}

fun String.getYear(): String {
    val dateList = this.split("-")
    return dateList[0]
}