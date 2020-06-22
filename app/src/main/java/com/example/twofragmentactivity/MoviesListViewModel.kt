package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel( moviesDataRepository: MoviesDataRepository) : ViewModel() {

    val movies : LiveData<Resource<List<MoviesListResponse>>> = moviesDataRepository.loadUser()
    fun getMovieListLiveData(): LiveData<Resource<List<MoviesListResponse>>> {
        return movies
    }
}