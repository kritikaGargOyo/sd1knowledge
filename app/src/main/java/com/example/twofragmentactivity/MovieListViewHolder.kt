package com.example.twofragmentactivity

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Movie
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.list_item.view.*


class MoviesListViewHolder( view: View , val movieListOnClickInterface: MovieListOnClickInterface) : RecyclerView.ViewHolder(view) {
    var name = view.textttt
    val image = view.image_movie


    fun updateData(moviesListResponse: MoviesListResponse, context: Context?) {
        name.text = moviesListResponse.title
       var queue: RequestQueue = Volley.newRequestQueue(context)
        val imageRequest = ImageRequest("https://image.tmdb.org/t/p/w500"+moviesListResponse.poster_path,
            Response.Listener<Bitmap?> { response ->
                image.setImageBitmap(response)
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
            Response.ErrorListener { error ->
                error.printStackTrace()
            })
        queue.add(imageRequest)

        image.setOnClickListener()
        {
            movieListOnClickInterface.onMovieClicked(moviesListResponse)
        }
    }
}