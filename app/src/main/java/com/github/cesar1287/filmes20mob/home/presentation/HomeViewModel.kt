package com.github.cesar1287.filmes20mob.home.presentation

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.github.cesar1287.filmes20mob.base.BaseViewModel
import com.github.cesar1287.filmes20mob.home.data.HomeDataSourceFactory
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.utils.Constants.Paging.PAGE_SIZE

class HomeViewModel(
    homeDataSourceFactory: HomeDataSourceFactory
) : BaseViewModel() {

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
}