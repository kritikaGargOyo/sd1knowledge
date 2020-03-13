package com.example.twofragmentactivity

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*


class MoviesListViewHolder(view: View, val movieListOnClickInterface: MovieListOnClickInterface) :
    RecyclerView.ViewHolder(view) {
    var name = view.movie_name
    var image = view.image_movie

    fun updateData(moviesListResponse: MoviesListResponse, context: Context) {
        name.text = moviesListResponse.title
        Glide.with(context).clear(image)
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + moviesListResponse.poster_path)
            .into(image)

        image.setOnClickListener()
        {
            movieListOnClickInterface.onMovieClicked(moviesListResponse)
        }
    }
}