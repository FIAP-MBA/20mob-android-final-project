package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.ui.favorite.FavoriteRepository
import com.github.cesar1287.filmes20mob.ui.favorite.FavoriteRepositoryImpl
import com.github.cesar1287.filmes20mob.ui.home.data.HomeRepository
import com.github.cesar1287.filmes20mob.ui.home.data.HomeRepositoryImpl
import com.github.cesar1287.filmes20mob.ui.home.data.HomeDataSourceFactory
import com.github.cesar1287.filmes20mob.ui.home.data.HomePageKeyedDataSource
import com.github.cesar1287.filmes20mob.ui.profile.data.ProfileRepository
import org.koin.dsl.module

object DataModule {

    val pagingModules = module {
        single {
            HomePageKeyedDataSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }
        single { HomeDataSourceFactory(tmdbDataSource = get()) }
    }

    val repositoryModules = module {
        single<HomeRepository> { HomeRepositoryImpl() }
        single<FavoriteRepository> { FavoriteRepositoryImpl() }
        single { ProfileRepository() }
    }
}