package com.example.twofragmentactivity.api

import com.example.twofragmentactivity.vo.MoviesApiResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBInterface {
    @GET("movie/trending/all/day")
    fun getTrendingMovies(@Query("page") page: Int): Single<MoviesApiResult>
}