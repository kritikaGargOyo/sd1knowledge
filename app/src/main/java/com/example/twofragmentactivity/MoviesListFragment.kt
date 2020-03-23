package com.example.twofragmentactivity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MoviesListFragment : Fragment(), MovieListOnClickInterface {
    private var rootview: View? = null
    var recyclerView: RecyclerView? = null
    var mAdapter: MoviesListAdapter? = null
    var progressBar: ProgressBar? = null
    private lateinit var viewModel: MoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootview = inflater.inflate(R.layout.movies_fragment_layout, container, false)
        val viewModelProvider = ViewModelProvider(requireActivity(), MoviesViewModelFactory())
        viewModel = viewModelProvider[MoviesListViewModel::class.java]
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = rootview?.findViewById(R.id.rv_list_movies)
        progressBar = rootview?.findViewById(R.id.loading)
        recyclerView?.layoutManager = GridLayoutManager(this.context, 2)
        mAdapter = MoviesListAdapter(this)
        recyclerView?.adapter = mAdapter


        viewModel.getMovieListLiveData().observe(viewLifecycleOwner, Observer {
            mAdapter?.submitList(it)
            progressBar?.visibility = View.GONE
        })
    }

    override fun onMovieClicked(moviesListResponse: MoviesListResponse?) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = MoviesDetailFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("response", moviesListResponse)
        }
        fragmentTransaction?.add(R.id.movies_fragment_container, fragment)
        fragmentTransaction?.addToBackStack(fragment.tag)
        fragmentTransaction?.commit()
    }

}