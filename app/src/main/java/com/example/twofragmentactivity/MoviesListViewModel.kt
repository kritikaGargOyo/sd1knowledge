package com.example.twofragmentactivity

import androidx.lifecycle.ViewModel

class MoviesListViewModel(var moviesDataRepository: MoviesDataRepository<MoviesListResponse>)
 : ViewModel() {
    fun getMovies() = moviesDataRepository.getMoviesList()
}