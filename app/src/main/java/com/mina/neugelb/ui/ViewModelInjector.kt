package com.mina.neugelb.ui

import com.mina.neugelb.ui.list.ListViewModel
import com.mina.neugelb.ui.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModules = module {
    single<SplashViewModel> {
        SplashViewModel(get())
    }

    single<ListViewModel> {
        ListViewModel(get())
    }
}