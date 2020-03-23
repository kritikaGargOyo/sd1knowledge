package com.example.twofragmentactivity

import androidx.lifecycle.LiveData


class MoviesDataRepository {
    private var movieDao: MovieDao

    init {
        val db = AppDatabase.getInstance()
        movieDao = db.movieDao()

    }

    fun getMovies() : LiveData<List<MoviesListResponse>>{
        val movies = movieDao.getAllMovies()
        return movies
    }
}
