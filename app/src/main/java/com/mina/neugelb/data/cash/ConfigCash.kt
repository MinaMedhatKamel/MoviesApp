package com.mina.neugelb

import com.mina.neugelb.data.model.ConfigData

/**
 * cash module for saving and retrieving the configuration request to use it globally during the app
 */
interface ConfigCash {
    fun saveConfigCash(configCash: ConfigData)
    fun getConfigCash(): ConfigData?
}

class ConfigCashImp() : ConfigCash {
    override fun saveConfigCash(configCash: ConfigData) {
        App.prefs?.config = configCash
    }

    override fun getConfigCash(): ConfigData? {
        return App.prefs?.config
    }

}

