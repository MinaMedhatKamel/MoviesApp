package com.mina.neugelb.ui.splash

import androidx.lifecycle.ViewModel
import com.mina.neugelb.data.model.ConfigData
import com.mina.neugelb.data.model.toImageConfigurationModel
import com.mina.neugelb.domain.ConfigUseCase
import com.mina.neugelb.ui.State
import com.mina.neugelb.utils.NetworkUtils
import kotlinx.coroutines.flow.flow


class SplashViewModel(private val configUseCase: ConfigUseCase) :
    ViewModel() {

    fun fetchConfig() = flow {
        emit(State.LoadingState)
        try {
            val configResponse = configUseCase.fetchConfig()
            configUseCase.saveConfigData(configResponse.toImageConfigurationModel())
            emit(State.DataState(configResponse))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkUtils.resolveError(e))
        }
    }

    fun getCachedData() =
        flow {
            emit(State.LoadingState)
            try {
                val savedData = configUseCase.getSavedConfigData()
                if (savedData == null) {
                    emit(State.ErrorState(java.lang.Exception("No cache avaibale")))
                } else {
                    emit(State.DataState(savedData))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(State.ErrorState(java.lang.Exception("cache exception!")))
            }
        }
}
