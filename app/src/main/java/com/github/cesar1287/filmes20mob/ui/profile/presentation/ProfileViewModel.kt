package com.github.cesar1287.filmes20mob.ui.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.ui.profile.domain.ProfileUseCase
import com.github.cesar1287.filmes20mob.utils.Command
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
): BaseViewModel() {
    private var _isUserLogged = MutableLiveData<Boolean>()

    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged

    fun isUserLogged() {
        viewModelScope.launch {
            command.postValue(Command.Loading(true))
            delay(1500)
            command.postValue(Command.Loading(false))
        }
    }

    fun loginUser() {
        //TODO
    }
}