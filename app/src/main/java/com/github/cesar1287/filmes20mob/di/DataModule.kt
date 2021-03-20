package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.home.data.HomeRepository
import com.github.cesar1287.filmes20mob.home.data.HomeRepositoryImpl
import com.github.cesar1287.filmes20mob.home.data.HomeDataSourceFactory
import com.github.cesar1287.filmes20mob.home.data.HomePageKeyedDataSource
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
    }
}