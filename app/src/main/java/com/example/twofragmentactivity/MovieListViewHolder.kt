package com.example.twofragmentactivity

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twofragmentactivity.vo.MoviesListResponse
import kotlinx.android.synthetic.main.list_item.view.*


class MoviesListViewHolder(view: View, val movieListOnClickInterface: MovieListOnClickInterface) :
    RecyclerView.ViewHolder(view) {
    var name = view.movie_name
    var image = view.image_movie

    fun updateData(moviesListResponse: MoviesListResponse?, context: Context) {
        name.text = moviesListResponse?.title
        Glide.with(context).clear(image)
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + moviesListResponse?.poster_path)
            .into(image)

        image.setOnClickListener()
        {
            movieListOnClickInterface.onMovieClicked(moviesListResponse)
        }
    }
}