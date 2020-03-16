package com.example.twofragmentactivity

import com.example.twofragmentactivity.vo.MoviesListResponse

interface MovieListOnClickInterface {
    fun onMovieClicked(moviesListResponse: MoviesListResponse?)
}