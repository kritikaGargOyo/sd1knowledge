package com.example.twofragmentactivity


class MoviesListPresenter(private val view: MoviesListFragment , val interactor: MoviesListInteractor = MoviesListInteractor()) {
    private var navigator : MoviesActivityNavigator = MoviesActivityNavigator(view.activity)


    private val moviesListResponseListener: MoviesListInteractor.MoviesListResponseListener = object : MoviesListInteractor.MoviesListResponseListener {
        override fun onMoviesListResponse(response: MoviesListResponse) { handleFirstMoviesListResponse(response) }
        override fun onError(errorMessage: String?) {}
    }

    private fun handleFirstMoviesListResponse(response: MoviesListResponse) {
        view.updateList(response)
    }

    fun setSearchRequest()
    {
        interactor.getMovies(moviesListResponseListener,view.context)
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


}