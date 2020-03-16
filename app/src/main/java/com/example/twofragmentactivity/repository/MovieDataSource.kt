package com.example.twofragmentactivity.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.twofragmentactivity.api.FIRST_PAGE
import com.example.twofragmentactivity.api.TheMovieDBInterface
import com.example.twofragmentactivity.vo.MoviesListResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, MoviesListResponse>() {

    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviesListResponse>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getTrendingMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe({

                    callback.onResult(it.movieList, null, page + 1)
                    networkState.postValue(NetworkState.LOADED)
                },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviesListResponse>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getTrendingMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.totalPages >= params.key) {
                        callback.onResult(it.movieList, params.key+1)
                        networkState.postValue(NetworkState.LOADED)

                    }
                    else
                    {
                        networkState.postValue(NetworkState.END_OF_LIST)
                    }
                },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviesListResponse>
    ) {
}
}