package com.github.cesar1287.filmes20mob.ui.profile.domain

import com.github.cesar1287.filmes20mob.model.Profile
import com.github.cesar1287.filmes20mob.ui.profile.data.ProfileRepository

class ProfileUseCase(private val repository: ProfileRepository) {

    fun isUserLogged(): Boolean = repository.isUserLogged()

    fun getUserProfile(): Profile = repository.getUserInfo()
}