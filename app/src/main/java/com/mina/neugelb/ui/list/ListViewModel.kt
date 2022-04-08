package com.mina.neugelb.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import com.mina.neugelb.data.model.MovieListUiModel
import com.mina.neugelb.data.paging.MoviesPagingSource
import com.mina.neugelb.domain.MoviesUseCase
import com.mina.neugelb.ui.State
import com.mina.neugelb.utils.NetworkUtils
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ListViewModel(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    var moviesData = Pager(PagingConfig(pageSize = 1)) {
        MoviesPagingSource(moviesUseCase)
    }.flow.cachedIn(viewModelScope)


    fun getMovies() = flow {
        emit(State.LoadingState)
        try {
            moviesData.collect {
                emit(State.DataState(it))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkUtils.resolveError(e))
        }
    }

    fun filterData(query: String) {
        moviesData = moviesData.map {
            it.filter {
                it.movieTitle.contains(query)
            }
        }
    }

    fun getAutoCompleteCurrentMoviesNames(items: List<MovieListUiModel>): List<String> {
        return items.map {
            it.movieTitle
        }
    }
}