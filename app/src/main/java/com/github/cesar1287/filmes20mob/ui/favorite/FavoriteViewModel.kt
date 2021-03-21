package com.github.cesar1287.filmes20mob.ui.favorite

import androidx.lifecycle.viewModelScope
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel() {

    fun getFavoriteMoviesFromUser() {
        viewModelScope.launch {
            callApi(
                call = suspend { favoriteUseCase.getFavoriteMoviesFromUser() },
                onSuccess = {

                }
            )
        }
    }
}