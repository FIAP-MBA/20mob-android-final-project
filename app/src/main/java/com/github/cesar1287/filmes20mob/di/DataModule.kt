package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.repository.ProfileRepository
import org.koin.dsl.module

object DataModule {
    val repositoryModules = module {
        single { ProfileRepository() }
    }
}