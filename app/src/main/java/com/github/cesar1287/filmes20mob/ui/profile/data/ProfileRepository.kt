package com.github.cesar1287.filmes20mob.ui.profile.data

import android.util.Log
import com.github.cesar1287.filmes20mob.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileRepository {

    fun isUserLogged(): Boolean {
        return Firebase.auth.currentUser != null && Firebase.auth.currentUser?.isAnonymous != true
    }

    fun getUserInfo(): Profile {
        val profile = Profile()
        Firebase.auth.currentUser?.let {
            profile.name = it.displayName ?: ""
            profile.email = it.email ?: ""
        }
        return profile
    }
}