package com.example.twofragmentactivity


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twofragmentactivity.api.TheMovieDBInterface
import com.example.twofragmentactivity.api.TheMovieDbClient
import com.example.twofragmentactivity.vo.MoviesListResponse


@Suppress("DEPRECATION")
class MoviesListFragment : Fragment(), MovieListOnClickInterface, MoviesListPresenter.View {

    private lateinit var viewModel: MovieListViewModel

    lateinit var movieRepository: MoviePagedListRepository
    private var rootview: View? = null
    var recyclerView: RecyclerView? = null
    var mAdapter: MoviesListAdapter? = null
    var progressBar: ProgressBar? = null
    var mPresenter: MoviesListPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    val apiService : TheMovieDBInterface = TheMovieDbClient.getClient()
    movieRepository = MoviePagedListRepository(apiService)
    viewModel = getViewModel()


        rootview = inflater.inflate(R.layout.movies_fragment_layout, container, false)
        val mInteractor = MoviesListInteractor(this.context)
        val mNavigator = MoviesActivityNavigator(this.activity)
        mPresenter = MoviesListPresenter(this, mInteractor,mNavigator)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = rootview?.findViewById<RecyclerView>(R.id.rv_list_movies)
        progressBar = rootview?.findViewById(R.id.loading)
        val gridLayoutManager = GridLayoutManager(this.context, 3)

//        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                val viewType = mAdapter?.getItemViewType(position)
//                if (viewType == mAdapter?.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
//                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
//            }
 //       };
        recyclerView?.layoutManager = GridLayoutManager(this.context , 2)
        recyclerView?.setHasFixedSize(true)
        mPresenter?.setSearchRequest()
        mAdapter = MoviesListAdapter(this.context as Context , this)
        recyclerView?.adapter = mAdapter


        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            mAdapter?.submitList(it)
            progressBar?.visibility = View.GONE
       })

//        viewModel.networkState.observe(this, Observer {
//                        progress_bar_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
//            txt_error_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE
//
//            if (!viewModel.listIsEmpty()) {
//                mAdapter.setNetworkState(it)
//            }
//        })
    }

    override fun updateList(response: MoviesListResponse) {
//        mAdapter?.responseList?.add(response)
//        mAdapter?.notifyDataSetChanged()
//        progressBar?.visibility = View.GONE
    }

    override fun onMovieClicked(moviesListResponse: MoviesListResponse?) {
        mPresenter?.getFilterFooterListener()?.onMovieItemClick(moviesListResponse)
    }

    private fun getViewModel(): MovieListViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieListViewModel(movieRepository) as T
            }
        })[MovieListViewModel::class.java]
    }

}