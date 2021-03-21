package com.github.cesar1287.filmes20mob.di

import com.github.cesar1287.filmes20mob.di.AppModule.viewModelModules
import com.github.cesar1287.filmes20mob.di.DataModule.pagingModules
import com.github.cesar1287.filmes20mob.di.DataModule.repositoryModules
import com.github.cesar1287.filmes20mob.di.DomainModule.domainModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getDomainModules(), *getDataModules(), *getViewModelModules())

    private fun getViewModelModules(): Array<Module> = arrayOf(viewModelModules)

    private fun getDomainModules(): Array<Module> = arrayOf(domainModules)

    private fun getDataModules(): Array<Module> = arrayOf(repositoryModules, pagingModules)
}