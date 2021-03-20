package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.home.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModules = module {
        viewModel { HomeViewModel(get()) }
    }
}