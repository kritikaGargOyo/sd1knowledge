package com.example.twofragmentactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel(var moviesDataRepository: MoviesDataRepository) : ViewModel() {

    private var mMovieList = MutableLiveData<List<MoviesListResponse>>()
    fun getMovieListLiveData(): LiveData<List<MoviesListResponse>> {
        getMovies()
        return mMovieList
    }

    fun getMovies() {
        moviesDataRepository.getMovies(object : ResponseCallback<MoviesResponse>() {
            override fun onSuccess(response: MoviesResponse) {
                Log.d("movieList Update", "Success")
                mMovieList.value = response.results
            }
        })
    }
}