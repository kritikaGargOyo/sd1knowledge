package com.example.twofragmentactivity.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.twofragmentactivity.api.TheMovieDBInterface
import com.example.twofragmentactivity.vo.MoviesListResponse
import io.reactivex.disposables.CompositeDisposable


class MovieDataSourceFactory(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, MoviesListResponse>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MoviesListResponse> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}