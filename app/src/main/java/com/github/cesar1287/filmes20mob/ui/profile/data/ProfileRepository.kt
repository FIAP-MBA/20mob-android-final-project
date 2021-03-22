package com.github.cesar1287.filmes20mob.ui.profile.data

import android.net.Uri
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.model.Profile
import com.google.firebase.auth.UserProfileChangeRequest
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
            profile.image = it.photoUrl
        }
        return profile
    }

    fun updateUserInfo(
        name: String,
        image: Uri? = null,
        onComplete: (isSuccessful: Boolean, message: Int) -> Unit
    ) {
        val user = Firebase.auth.currentUser

        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name)
        image?.let {
            profileUpdates.photoUri = it
        }

        user?.updateProfile(profileUpdates.build())?.addOnCompleteListener { task ->
            val error = R.string.error_default
            onComplete(task.isSuccessful, error)
        } ?: onComplete(false, R.string.error_default)
    }
}