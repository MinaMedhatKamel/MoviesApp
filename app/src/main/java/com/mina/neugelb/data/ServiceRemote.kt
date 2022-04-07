package com.mina.neugelb.data

import com.mina.neugelb.BuildConfig
import com.mina.neugelb.data.model.ConfigResponse
import com.mina.neugelb.data.model.LatestMoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceRemote {
    @GET(BuildConfig.BASE_URL + "configuration?api_key=" + BuildConfig.API_KEY)
    suspend fun getConfiguration(): ConfigResponse


    @GET(BuildConfig.BASE_URL + "movie/now_playing?language=en-US&api_key="+ BuildConfig.API_KEY)
    suspend fun getLatestMovies(@Query("page") page: Int): LatestMoviesResponse
}