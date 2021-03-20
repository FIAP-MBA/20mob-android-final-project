package com.github.cesar1287.filmes20mob.home.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.github.cesar1287.filmes20mob.model.MovieItem

class HomeDataSourceFactory(
    private val tmdbDataSource: HomePageKeyedDataSource
): DataSource.Factory<Int, MovieItem>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, MovieItem>>()
    override fun create(): DataSource<Int, MovieItem> {
        tmdbLiveDataSource.postValue(tmdbDataSource)
        return tmdbDataSource
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, MovieItem>> {
        return tmdbLiveDataSource
    }
}