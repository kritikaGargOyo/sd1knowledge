package com.example.twofragmentactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment

class MoviesDetailFragment : AppCompatDialogFragment() {

    private var rootview: View? = null
    private var moviesListResponse: MoviesListResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootview = inflater.inflate(R.layout.movies_detail_layout, container, false)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesListResponse = arguments?.getParcelable("response")
        rootview?.findViewById<TextView>(R.id.overview)?.setText(moviesListResponse?.overview)
        rootview?.findViewById<TextView>(R.id.rating)
            ?.setText(moviesListResponse?.vote_average.toString())
        rootview?.findViewById<TextView>(R.id.title)?.setText(moviesListResponse?.title)
        rootview?.findViewById<TextView>(R.id.release_date)
            ?.setText(moviesListResponse?.release_date)
    }
}

