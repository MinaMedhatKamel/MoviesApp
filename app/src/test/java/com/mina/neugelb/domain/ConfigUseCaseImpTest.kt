package com.mina.neugelb.domain

import com.mina.neugelb.ConfigCash
import com.mina.neugelb.FakeConfigResponse
import com.mina.neugelb.data.repository.ConfigRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ConfigUseCaseImpTest {

    @MockK
    lateinit var configRepo: ConfigRepository

    @MockK
    lateinit var configCash: ConfigCash

    lateinit var configUseCase: ConfigUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        configUseCase = ConfigUseCaseImp(configRepo, configCash)
    }

    @Test
    fun `test call fetch config then call getconfig from the repo`() {
        coEvery { configRepo.getConfig() } returns FakeConfigResponse.getFakeConfigResponse()

        // when
        val data = runBlocking { configUseCase.fetchConfig() }

        //then
        coVerify(exactly = 1) { configRepo.getConfig() }
    }

    @Test
    fun `test call getSavedConfigData should call getconfig from the cash`() {
        coEvery { configCash.getConfigCache() } returns FakeConfigResponse.getFakeCashedData()

        // when
        val data = runBlocking { configUseCase.getSavedConfigData() }

        //then
        coVerify(exactly = 1) { configCash.getConfigCache() }
    }

}