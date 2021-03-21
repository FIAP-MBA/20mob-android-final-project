package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.ui.favorite.presentation.FavoriteViewModel
import com.github.cesar1287.filmes20mob.ui.home.presentation.HomeViewModel
import com.github.cesar1287.filmes20mob.ui.profile.presentation.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val viewModelModules = module {
        viewModel {
            HomeViewModel(
                homeDataSourceFactory = get(),
                homeUseCase = get()
            )
        }
        viewModel { FavoriteViewModel(favoriteUseCase = get()) }
        viewModel { ProfileViewModel(profileUseCase = get()) }
    }
}