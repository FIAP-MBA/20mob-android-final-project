package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.home.domain.HomeUseCase
import org.koin.dsl.module

object DomainModule {

    val domainModules = module {
        single { HomeUseCase() }
    }
}