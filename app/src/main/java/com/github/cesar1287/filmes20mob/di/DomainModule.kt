package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.ui.home.domain.HomeUseCase
import com.github.cesar1287.filmes20mob.ui.profile.domain.ProfileUseCase
import org.koin.dsl.module

object DomainModule {
    val domainModules = module {
        single { HomeUseCase(homeRepository = get()) }
        single { ProfileUseCase(repository = get()) }
    }
}