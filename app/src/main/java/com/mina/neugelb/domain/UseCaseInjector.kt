package com.mina.neugelb.domain

import org.koin.dsl.module

val useCaseModules = module {
    single<ConfigUseCase> {
        ConfigUseCaseImp(get(),get())
    }
    single<MoviesUseCase> {
        MoviesUseCaseImp(get(),get())
    }
}