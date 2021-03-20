package com.github.cesar1287.filmes20mob.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.filmes20mob.useCase.ProfileUseCase
import com.github.cesar1287.filmes20mob.util.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
): ViewModel() {
    private var _isUserLogged = MutableLiveData<Response<Boolean>>()

    val isUserLogged: LiveData<Response<Boolean>>
        get() = _isUserLogged

    fun isUserLogged() {
        _isUserLogged.postValue(Response.Loading())
        viewModelScope.launch {
            delay(1500)
            _isUserLogged.postValue(Response.Error(message = "Teste"))
        }
    }

    fun loginUser() {
        //TODO
    }
}