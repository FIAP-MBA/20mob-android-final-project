package com.github.cesar1287.filmes20mob.ui.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.esafirm.imagepicker.model.Image
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.model.Profile
import com.github.cesar1287.filmes20mob.ui.profile.domain.ProfileUseCase
import com.github.cesar1287.filmes20mob.utils.Command
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
): BaseViewModel() {
    private var _isUserLogged = MutableLiveData<Boolean>()
    private var _userProfile = MutableLiveData<Profile>()

    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged
    val userProfile: LiveData<Profile>
        get() = _userProfile

    fun isUserLogged() {
        command.postValue(Command.Loading(true))
        _isUserLogged.postValue(profileUseCase.isUserLogged())
        command.postValue(Command.Loading(false))
    }

    fun loadUser() {
        _userProfile.postValue(profileUseCase.getUserProfile())
    }

    fun updateUser(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        image: Image?
    ) {
        //TODO
    }
}