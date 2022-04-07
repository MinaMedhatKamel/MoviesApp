package com.mina.neugelb.data

import com.mina.neugelb.ConfigCash
import com.mina.neugelb.ConfigCashImp
import com.mina.neugelb.data.repository.ConfigRepository
import com.mina.neugelb.data.repository.ConfigRepositoryImp
import com.mina.neugelb.data.repository.MoviesRepository
import com.mina.neugelb.data.repository.MoviesRepositoryImp

import org.koin.dsl.module

val DataModule = module {
    single<ConfigCash> {
        ConfigCashImp()
    }
    single<ConfigRepository> {
        ConfigRepositoryImp(get())
    }
    single<MoviesRepository> {
        MoviesRepositoryImp(get())
    }
}