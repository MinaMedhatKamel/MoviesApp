package com.mina.neugelb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mina.neugelb.data.model.MovieListUiModel
import com.mina.neugelb.domain.MoviesUseCase

/**
 * Manages the api pagination and fetch the data from [moviesUseCase]
 */
class MoviesPagingSource(private val moviesUseCase: MoviesUseCase) :
    PagingSource<Int, MovieListUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieListUiModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, MovieListUiModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = moviesUseCase.getLatestMovies(currentPage)
            val responseData = mutableListOf<MovieListUiModel>()
            val data = response ?: emptyList()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}