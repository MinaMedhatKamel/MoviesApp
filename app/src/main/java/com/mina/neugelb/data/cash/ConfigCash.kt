package com.mina.neugelb

import com.mina.neugelb.data.model.ConfigData

/**
 * cash module for saving and retrieving the configuration request to use it globally during the app
 */
interface ConfigCash {
    fun saveConfigCache(configCache: ConfigData)
    fun getConfigCache(): ConfigData?
}

class ConfigCashImp() : ConfigCash {
    override fun saveConfigCache(configCache: ConfigData) {
        App.prefs?.config = configCache
    }

    override fun getConfigCache(): ConfigData? {
        return App.prefs?.config
    }

}

