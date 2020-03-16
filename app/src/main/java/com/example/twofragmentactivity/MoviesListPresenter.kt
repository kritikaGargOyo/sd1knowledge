package com.example.twofragmentactivity

import android.view.View


class MoviesListPresenter(
    val view: View,
    val interactor: MoviesListInteractor,
    private var navigator: MoviesActivityNavigator
) {

    private val moviesListResponseListener: MoviesListInteractor.MoviesListResponseListener =
        object : MoviesListInteractor.MoviesListResponseListener {
            override fun onMoviesListResponse(response: MoviesListResponse) {
                handleFirstMoviesListResponse(response)
            }

            override fun onError(errorMessage: String?) {}
        }

    private fun handleFirstMoviesListResponse(response: MoviesListResponse) {
        view.updateList(response)
    }

    fun setSearchRequest() {
        interactor.getMovies(moviesListResponseListener)
    }

    private val movieClickListener: MovieClickListener =
        object : MovieClickListener {
            override fun onMovieItemClick(moviesListResponse: MoviesListResponse) {
                navigator.openDetailFragment(moviesListResponse)
            }

        }

    fun getFilterFooterListener(): MovieClickListener? {
        return movieClickListener
    }

     interface View {
        fun updateList(response: MoviesListResponse)
    }
}