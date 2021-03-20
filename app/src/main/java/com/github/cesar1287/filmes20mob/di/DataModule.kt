package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.home.data.HomeRepository
import com.github.cesar1287.filmes20mob.home.data.HomeRepositoryImpl
import com.github.cesar1287.filmes20mob.home.data.HomeDataSourceFactory
import com.github.cesar1287.filmes20mob.home.data.HomePageKeyedDataSource
import org.koin.dsl.module

object DataModule {

    val pagingModules = module {
        single { HomePageKeyedDataSource(get(), get()) }
        single { HomeDataSourceFactory(get()) }
    }

    val repositoryModules = module {
        single<HomeRepository> { HomeRepositoryImpl() }
    }
}