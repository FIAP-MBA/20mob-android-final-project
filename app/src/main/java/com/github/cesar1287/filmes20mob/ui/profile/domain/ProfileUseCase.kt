package com.github.cesar1287.filmes20mob.ui.profile.domain

import android.net.Uri
import com.github.cesar1287.filmes20mob.model.Profile
import com.github.cesar1287.filmes20mob.ui.profile.data.ProfileRepository

class ProfileUseCase(private val repository: ProfileRepository) {

    fun isUserLogged(): Boolean = repository.isUserLogged()

    fun getUserProfile(): Profile = repository.getUserInfo()

    fun updateUserInfo(name: String, image: String? = null, onComplete: (isSuccessful: Boolean, message: String?) -> Unit) {
        image?.let { img ->
            Uri.parse(img)?.let {
                repository.updateUserInfo(name, it, onComplete)
            } ?: repository.updateUserInfo(name, onComplete =  onComplete)
        } ?: repository.updateUserInfo(name, onComplete =  onComplete)
    }
}