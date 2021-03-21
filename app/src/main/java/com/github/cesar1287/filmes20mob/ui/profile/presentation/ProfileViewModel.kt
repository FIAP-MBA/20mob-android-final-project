package com.github.cesar1287.filmes20mob.ui.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.esafirm.imagepicker.model.Image
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.model.Profile
import com.github.cesar1287.filmes20mob.ui.profile.domain.ProfileUseCase
import com.github.cesar1287.filmes20mob.utils.Command

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
) : BaseViewModel() {
    private var _isUserLogged = MutableLiveData<Boolean>()
    private var _userProfile = MutableLiveData<Profile>()
    private var _isProfileUpdated = MutableLiveData<Boolean>()

    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged
    val userProfile: LiveData<Profile>
        get() = _userProfile
    val isProfileUpdated: LiveData<Boolean>
        get() = _isProfileUpdated

    fun isUserLogged() {
        command.postValue(Command.Loading(true))
        _isUserLogged.postValue(profileUseCase.isUserLogged())
        command.postValue(Command.Loading(false))
    }

    fun loadUser() {
        command.postValue(Command.Loading(true))
        _userProfile.postValue(profileUseCase.getUserProfile())
        command.postValue(Command.Loading(false))
    }

    fun updateUser(
        name: String,
        image: Image?
    ) {
        command.postValue(Command.Loading(value = true))
        image?.let {
            profileUseCase.updateUserInfo(name, it.path, ::handlerFirebaseCalls)
        } ?: profileUseCase.updateUserInfo(name, onComplete = ::handlerFirebaseCalls)
    }

    private fun handlerFirebaseCalls(isSuccessful: Boolean, message: String?) {
        command.postValue(Command.Loading(false))
        _isProfileUpdated.postValue(isSuccessful)
        if (!isSuccessful) {
            command.postValue(Command.Error(error = message))
        }
    }
}