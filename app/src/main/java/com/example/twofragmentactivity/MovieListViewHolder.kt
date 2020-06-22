package com.example.twofragmentactivity

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.View
import android.view.animation.AnimationSet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*


class MoviesListViewHolder(view: View, val movieListOnClickInterface: MovieListOnClickInterface) :
    RecyclerView.ViewHolder(view) {
    var name = view.movie_name
    var image = view.image_movie
    var icon = view.star_button
    lateinit var mygestureDetector: GestureDetector


    @SuppressLint("ClickableViewAccessibility")
    fun updateData(moviesListResponse: MoviesListResponse? , lifecycleOwner: LifecycleOwner) {
        name.text = moviesListResponse?.title
        icon.setPadding(4,8,0,0)
        Glide.with(MyApplication.getAppContext()).clear(image)
        Glide.with(MyApplication.getAppContext())
            .load("https://image.tmdb.org/t/p/w500" + moviesListResponse?.poster_path)
            .into(image)

        val gestureListener = GestureListener(movieListOnClickInterface, moviesListResponse)
        mygestureDetector = GestureDetector(MyApplication.getAppContext(), gestureListener)
        val touchListener =
            View.OnTouchListener { v, event -> mygestureDetector.onTouchEvent(event) }

        image.setOnTouchListener(touchListener)

        gestureListener.getIsSelected().observe(lifecycleOwner, Observer {
            if (it == true) {
                image.setPadding(20, 20, 20, 20)
                icon.setPadding(24,32,0,0)
                icon.isLiked = true
            }
            else {
                image.setPadding(0,0,0,0)
                icon.setPadding(4,8,0,0)
                icon.isLiked = false
            }
        })
    }
}