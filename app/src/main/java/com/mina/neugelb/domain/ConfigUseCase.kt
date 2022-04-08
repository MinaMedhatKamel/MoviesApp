package com.mina.neugelb.domain

import com.mina.neugelb.ConfigCash
import com.mina.neugelb.data.model.ConfigData
import com.mina.neugelb.data.model.ConfigResponse
import com.mina.neugelb.data.repository.ConfigRepository


interface ConfigUseCase {
    suspend fun fetchConfig(): ConfigResponse
    suspend fun saveConfigData(configData: ConfigData)
    suspend fun getSavedConfigData(): ConfigData?
}

/**
 * use case for:
 * - manipulating the data comming from the [ConfigRepository]
 * - saving and retrieving the config using the [ConfigCash]
 */
class ConfigUseCaseImp(val configRepo: ConfigRepository, val configCash: ConfigCash) :
    ConfigUseCase {
    override suspend fun fetchConfig(): ConfigResponse {
        return configRepo.getConfig()
    }

    override suspend fun saveConfigData(configData: ConfigData) {
        configCash.saveConfigCache(configData)
    }

    override suspend fun getSavedConfigData(): ConfigData? {
        return configCash.getConfigCache()
    }
}