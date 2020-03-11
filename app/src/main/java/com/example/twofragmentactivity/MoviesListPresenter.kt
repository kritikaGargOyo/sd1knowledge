package com.example.twofragmentactivity


class MoviesListPresenter(private val view: MoviesListFragment) {
    private var interactor: MoviesListInteractor? = null

    init {
        interactor = MoviesListInteractor()
    }

    private val moviesListResponseListener: MoviesListInteractor.MoviesListResponseListener = object : MoviesListInteractor.MoviesListResponseListener {
        override fun onMoviesListResponse(response: MoviesListResponse) { handleFirstMoviesListResponse(response) }
        override fun onError(errorMessage: String?) {}
    }

    private fun handleFirstMoviesListResponse(response: MoviesListResponse) {
        view.updateList(response)
    }

    fun setSearchRequest()
    {
        interactor?.getMovies(moviesListResponseListener,view.context)
    }

}