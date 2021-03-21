package com.github.cesar1287.filmes20mob.extensions

import com.github.cesar1287.filmes20mob.utils.Constants
import com.github.cesar1287.filmes20mob.utils.ImageSizeEnum

fun String.getPosterImagePath(size: ImageSizeEnum = ImageSizeEnum.SMALL): String {
    return when (size) {
        ImageSizeEnum.SMALL -> "${Constants.Api.BASE_URL_W154_IMAGE}$this"
        ImageSizeEnum.MEDIUM -> "${Constants.Api.BASE_URL_W185_IMAGE}$this"
        ImageSizeEnum.LARGE -> "${Constants.Api.BASE_URL_W500_IMAGE}$this"
    }
}

fun String.getYear(): String {
    val dateList = this.split("-")
    return dateList[0]
}