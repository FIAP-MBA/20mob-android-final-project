package com.github.cesar1287.filmes20mob.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    var name: String = "",
    var image: String = "",
    var email: String = "",
    var bio: String = ""
): Parcelable
