package com.mina.neugelb.domain

import com.mina.neugelb.ConfigCash
import com.mina.neugelb.FakeConfigResponse
import com.mina.neugelb.FakeMoviesResponse
import com.mina.neugelb.data.model.MovieListUiModel
import com.mina.neugelb.data.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoviesUseCaseImpTest {

    @MockK
    lateinit var moviesRepo: MoviesRepository

    @MockK
    lateinit var configCash: ConfigCash

    lateinit var moviesUseCase: MoviesUseCase

    private val movieUiItemSample =
        MovieListUiModel(
            id = 335787,
            imgUrl = "test HQ/sqLowacltbZLoCa4KYye64RvvdQ.jpg",
            movieTitle = "Uncharted",
            description = "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
            adult = false,
            vote_count = 892,
            voteAvg = 7.0f
        )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        moviesUseCase = MoviesUseCaseImp(moviesRepo, configCash)
    }

    @Test
    fun `test call getLatestMovies should call getLatestMovies from the repo`() {
        setupMock()

        // when
        runBlocking { moviesUseCase.getLatestMovies(1) }

        //then
        coVerify(exactly = 1) { moviesRepo.getLatestMovies(any()) }
    }

    @Test
    fun `test  call getLatestMovies should return correct data`() {
        setupMock()
        // when
        val data = runBlocking { moviesUseCase.getLatestMovies(1) }
        // assertion
        assertEquals(movieUiItemSample, data[0])
    }

    private fun setupMock() {
        coEvery { configCash.getConfigCash() } returns FakeConfigResponse.getFakeCashedData()
        coEvery { moviesRepo.getLatestMovies(any()) } returns FakeMoviesResponse.getFakeMoviesResponse()
    }


}