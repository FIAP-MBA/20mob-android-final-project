package com.github.cesar1287.filmes20mob.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val name: String,
    val image: String,
    val email: String,
    val bio: String
): Parcelable
