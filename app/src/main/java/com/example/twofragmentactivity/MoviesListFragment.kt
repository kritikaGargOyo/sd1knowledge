package com.example.twofragmentactivity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MoviesListFragment : AppCompatDialogFragment()
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
        recyclerView=rootview?.findViewById(R.id.rv_list_movies)
        progressBar= rootview?.findViewById(R.id.loading)
        recyclerView?.layoutManager = GridLayoutManager(this.context,2)
        mPresenter?.setSearchRequest()
        mAdapter = MoviesListAdapter(context)
        recyclerView?.adapter = mAdapter

    }

    fun updateList(response: MoviesListResponse) {
        mAdapter?.responseList?.add(response)
        mAdapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
    }
}