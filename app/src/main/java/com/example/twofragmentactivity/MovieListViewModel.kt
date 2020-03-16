package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.twofragmentactivity.repository.NetworkState
import com.example.twofragmentactivity.vo.MoviesListResponse
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(private val moviePagedListRepository: MoviePagedListRepository) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val moviePagedList: LiveData<PagedList<MoviesListResponse>> by lazy {
        moviePagedListRepository.fetchMoviesList(compositeDisposable)
    }
    val networkState: LiveData<NetworkState> by lazy {
        moviePagedListRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}