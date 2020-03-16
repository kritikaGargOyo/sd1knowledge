package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.twofragmentactivity.api.POST_PER_PAGE
import com.example.twofragmentactivity.api.TheMovieDBInterface
import com.example.twofragmentactivity.repository.MovieDataSource
import com.example.twofragmentactivity.repository.MovieDataSourceFactory
import com.example.twofragmentactivity.repository.NetworkState
import com.example.twofragmentactivity.vo.MoviesListResponse
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : TheMovieDBInterface )
{
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchMoviesList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<MoviesListResponse>>
    {
        movieDataSourceFactory = MovieDataSourceFactory(apiService,compositeDisposable)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()
        return LivePagedListBuilder(movieDataSourceFactory,config).build()
    }

    fun getNetworkState():LiveData<NetworkState>{
        return Transformations.switchMap<MovieDataSource,NetworkState>(
            movieDataSourceFactory.movieLiveDataSource,MovieDataSource::networkState)
    }
}