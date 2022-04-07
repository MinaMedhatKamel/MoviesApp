package com.mina.neugelb

import android.app.Application
import com.mina.neugelb.core.retrofitModule
import com.mina.neugelb.data.DataModule
import com.mina.neugelb.domain.useCaseModules
import com.mina.neugelb.ui.viewModelModules
import com.mina.neugelb.utils.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class App : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            koin.loadModules(listOf(retrofitModule, DataModule, useCaseModules, viewModelModules))
        }
    }
}