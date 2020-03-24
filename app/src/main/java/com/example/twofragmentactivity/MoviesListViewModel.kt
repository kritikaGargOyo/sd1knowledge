package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel( moviesDataRepository: MoviesDataRepository) : ViewModel() {

    val movies : LiveData<List<MoviesListResponse>> = moviesDataRepository.getMovies()
    fun getMovieListLiveData(): LiveData<List<MoviesListResponse>> {
        return movies
    }
}