package com.mina.neugelb.data.repository

import com.mina.neugelb.data.ServiceRemote
import com.mina.neugelb.data.model.ConfigResponse

interface ConfigRepository {
    suspend fun getConfig(): ConfigResponse
}

/**
 * repo for getting the configuration using the [ServiceRemote] interface
 */
class ConfigRepositoryImp(val remote: ServiceRemote) : ConfigRepository {
    override suspend fun getConfig(): ConfigResponse {
        return remote.getConfiguration()
    }
}
