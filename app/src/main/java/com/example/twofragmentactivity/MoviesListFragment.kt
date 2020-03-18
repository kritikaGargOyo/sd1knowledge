package com.example.twofragmentactivity


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MoviesListFragment : Fragment(), MovieListOnClickInterface, MoviesListPresenter.View {
    private var rootview: View? = null
    var recyclerView: RecyclerView? = null
    var mAdapter: MoviesListAdapter? = null
    var progressBar: ProgressBar? = null
    var mPresenter: MoviesListPresenter? = null
    var viewModel:MoviesListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootview = inflater.inflate(R.layout.movies_fragment_layout, container, false)
      //  val mInteractor = MoviesListInteractor(this.context)
       // val mNavigator = MoviesActivityNavigator(this.activity)
      //  mPresenter = MoviesListPresenter(this, mInteractor,mNavigator)
        val moviesDataRepo = MoviesDataRepositoryImpl(this.context)
        viewModel = MoviesListViewModel(moviesDataRepo)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = rootview?.findViewById<RecyclerView>(R.id.rv_list_movies)
        progressBar = rootview?.findViewById(R.id.loading)
        recyclerView?.layoutManager = GridLayoutManager(this.context, 2)
        mAdapter = MoviesListAdapter(context as Context, this)
       // mPresenter?.setSearchRequest()
        viewModel?.getMovies()?.observe(viewLifecycleOwner , Observer {
            mAdapter?.responseList = it
            progressBar?.visibility = View.GONE
        })
        recyclerView?.adapter = mAdapter
    }

//   override fun updateList(response: List<MoviesListResponse>) {
////        mAdapter?.responseList = response
////        mAdapter?.notifyDataSetChanged()
////        progressBar?.visibility = View.GONE
//    }

    override fun onMovieClicked(moviesListResponse: MoviesListResponse) {
     //   mPresenter?.getFilterFooterListener()?.onMovieItemClick(moviesListResponse)
    }

    override fun updateList(response: MoviesListResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}