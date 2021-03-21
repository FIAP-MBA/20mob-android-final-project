package com.github.cesar1287.filmes20mob.ui.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.ui.favorite.domain.FavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel() {

    private val _onFavoriteMoviesLoaded: MutableLiveData<List<MovieItem>> = MutableLiveData()

    val onFavoriteMoviesLoaded: LiveData<List<MovieItem>>
        get() = _onFavoriteMoviesLoaded

    private val _onFavoriteMovieRemoved: MutableLiveData<MovieItem> = MutableLiveData()

    val onFavoriteMovieRemoved: LiveData<MovieItem>
        get() = _onFavoriteMovieRemoved

    fun getFavoriteMoviesFromUser() {
        viewModelScope.launch {
            callApi(
                call = suspend { favoriteUseCase.getFavoriteMoviesFromUser() },
                onSuccess = {
                    val movies = it as MutableList<*>
                    _onFavoriteMoviesLoaded.postValue(movies.filterIsInstance<MovieItem>())
                }
            )
        }
    }

    fun removeMovieFromFavorites(favoriteRemoved: MovieItem) {
        viewModelScope.launch {
            callApi(
                call = suspend { favoriteUseCase.removeMovieFromFavorites(favoriteRemoved) },
                onSuccess = {
                    _onFavoriteMovieRemoved.postValue(favoriteRemoved)
                }
            )
        }
    }
}