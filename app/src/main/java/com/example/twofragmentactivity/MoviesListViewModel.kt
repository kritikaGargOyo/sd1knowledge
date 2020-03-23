package com.example.twofragmentactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel(var moviesDataRepository: MoviesDataRepository) : ViewModel() {

    fun getMovieListLiveData(): LiveData<List<MoviesListResponse>> {
        return moviesDataRepository.getMovies()
    }
}