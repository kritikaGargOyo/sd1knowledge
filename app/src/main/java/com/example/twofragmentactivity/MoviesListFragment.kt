package com.example.twofragmentactivity


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MoviesListFragment : Fragment() , MovieListOnClickInterface
{
    private var rootview: View? = null
    var recyclerView: RecyclerView? = null
    var mAdapter:MoviesListAdapter? = null
    var progressBar: ProgressBar? = null
    var mPresenter: MoviesListPresenter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       rootview  = inflater.inflate(R.layout.movies_fragment_layout,container,false)
        mPresenter = MoviesListPresenter(this)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=rootview?.findViewById<RecyclerView>(R.id.rv_list_movies)
        progressBar= rootview?.findViewById(R.id.loading)
        recyclerView?.layoutManager = GridLayoutManager(this.context,2)
        mPresenter?.setSearchRequest()
        mAdapter = MoviesListAdapter(context as Context,this)
        recyclerView?.adapter = mAdapter
    }

    fun updateList(response: MoviesListResponse) {
        mAdapter?.responseList?.add(response)
        mAdapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
    }

    override fun onMovieClicked(moviesListResponse: MoviesListResponse) {
        mPresenter?.getFilterFooterListener()?.onMovieItemClick(moviesListResponse)
    }

}