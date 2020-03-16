package com.example.twofragmentactivity

import com.example.twofragmentactivity.vo.MoviesListResponse

interface MovieClickListener {
    fun onMovieItemClick(moviesListResponse: MoviesListResponse?)
}
