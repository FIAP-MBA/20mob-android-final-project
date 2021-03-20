package com.github.cesar1287.filmes20mob.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.home.data.HomeDataSourceFactory
import com.github.cesar1287.filmes20mob.home.domain.HomeUseCase
import com.github.cesar1287.filmes20mob.model.Genre
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.Constants.Paging.PAGE_SIZE
import kotlinx.coroutines.launch

class HomeViewModel(
    homeDataSourceFactory: HomeDataSourceFactory,
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val _onGenresLoaded: MutableLiveData<Genre> = MutableLiveData()

    val onGenresLoaded: LiveData<Genre>
        get() = _onGenresLoaded

    var moviesPagedList: LiveData<PagedList<MovieItem>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, MovieItem>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = homeDataSourceFactory.getLiveDataSource()
        moviesPagedList = LivePagedListBuilder(homeDataSourceFactory, pagedListConfig)
            .build()

    }

    fun loadGenres() {
        viewModelScope.launch {
            callApi(
                call = suspend { homeUseCase.loadGenres() },
                onSuccess = {
                    _onGenresLoaded.postValue(it as? Genre)
                },
                onError = {
                    _onGenresLoaded.postValue(null)
                }
            )
        }
    }
}