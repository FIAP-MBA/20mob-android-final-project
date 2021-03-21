package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.ui.home.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import com.github.cesar1287.filmes20mob.ui.aboutUs.presentation.AboutUsViewModel
import com.github.cesar1287.filmes20mob.ui.profile.presentation.ProfileViewModel
import org.koin.dsl.module

object AppModule {
    val viewModelModules = module {
        viewModel {
            HomeViewModel(
                homeDataSourceFactory = get(),
                homeUseCase = get()
            )
        }
        viewModel { AboutUsViewModel() }
        viewModel { ProfileViewModel(profileUseCase = get()) }
    }
}