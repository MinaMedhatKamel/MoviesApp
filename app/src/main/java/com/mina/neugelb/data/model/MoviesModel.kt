package com.mina.neugelb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LatestMoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

@Parcelize
data class MovieListUiModel(
    val id: Int,
    val imgUrl: String,
    val movieTitle: String,
    val description: String,
    val adult: Boolean,
    val voteAvg: Float,
    val vote_count: Int
) : Parcelable

fun Result.toMovieListUiModel(imgBaseUrl: String): MovieListUiModel {
    return MovieListUiModel(
        id,
        imgBaseUrl + poster_path,
        title,
        overview,
        adult,
        vote_average,
        vote_count
    )
}