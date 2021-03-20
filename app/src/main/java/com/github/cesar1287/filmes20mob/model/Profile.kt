package com.github.cesar1287.filmes20mob.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    var name: String = "",
    var image: Uri? = null,
    var email: String = ""
): Parcelable
