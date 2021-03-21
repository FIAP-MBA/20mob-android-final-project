package com.github.cesar1287.filmes20mob.ui.home.data

import androidx.paging.PageKeyedDataSource
import com.github.cesar1287.filmes20mob.ui.home.domain.HomeUseCase
import com.github.cesar1287.filmes20mob.model.MovieItem
import com.github.cesar1287.filmes20mob.model.MoviesResults
import com.github.cesar1287.filmes20mob.utils.Constants.Paging.FIRST_PAGE
import com.github.cesar1287.filmes20mob.utils.ResponseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomePageKeyedDataSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PageKeyedDataSource<Int, MovieItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieItem>
    ) {
        CoroutineScope(IO).launch {
            val movies: List<MovieItem> = callNowPlayingMoviesApi(FIRST_PAGE)
            callback.onResult(movies, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, MovieItem>) {
        CoroutineScope(IO).launch {
            val movies: List<MovieItem> = callNowPlayingMoviesApi(page)
            callback.onResult(movies, nextPage)
        }
    }

    private suspend fun callNowPlayingMoviesApi(page: Int): List<MovieItem> {
        return when (
            val response = homeRepository.getNowPlayingMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? MoviesResults
                homeUseCase.setupMoviesList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }
}