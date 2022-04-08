package com.mina.neugelb.domain


import com.mina.neugelb.BuildConfig
import com.mina.neugelb.ConfigCash
import com.mina.neugelb.data.model.*
import com.mina.neugelb.data.repository.MoviesRepository


interface MoviesUseCase {
    suspend fun getLatestMovies(page: Int): List<MovieListUiModel>
}

/**
 * use case for:
 * - manipulating the data comming from the [MoviesRepository] and [ConfigCash]
 */
class MoviesUseCaseImp(val moviesRpo: MoviesRepository, val configCash: ConfigCash) :
    MoviesUseCase {
    override suspend fun getLatestMovies(page: Int): List<MovieListUiModel> {
        return moviesRpo.getLatestMovies(page).results.map {
            it.toMovieListUiModel(
                configCash.getConfigCache()?.imgBaseUrlHQ ?: BuildConfig.DEFAULT_BASE_IMG_URL
            )
        }
    }
}