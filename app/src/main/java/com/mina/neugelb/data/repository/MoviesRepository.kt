package com.mina.neugelb.data.repository

import com.mina.neugelb.data.ServiceRemote
import com.mina.neugelb.data.model.LatestMoviesResponse
import com.mina.neugelb.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MoviesRepository{
    suspend fun getLatestMovies(page:Int) : LatestMoviesResponse
}
/**
 * repo for getting the configuration using the [ServiceRemote] interface
 */
class MoviesRepositoryImp(val remote: ServiceRemote) : MoviesRepository{
    override suspend fun getLatestMovies(page: Int): LatestMoviesResponse {
        return remote.getLatestMovies(page)
    }
}