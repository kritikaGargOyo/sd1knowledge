package com.example.twofragmentactivity

import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class GestureListener(
    val movieListOnClickInterface: MovieListOnClickInterface,
    val moviesListResponse: MoviesListResponse?
) : GestureDetector.SimpleOnGestureListener() {
    private var isSelected = MutableLiveData<Boolean>()

    override fun onDown(e: MotionEvent?): Boolean {
        super.onDown(e)
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        moviesListResponse?.apply { isLiked = isLiked != true }
        isSelected.value = isSelected.value != true
        return super.onDoubleTap(e)
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        movieListOnClickInterface.onMovieClicked(moviesListResponse)
        Toast.makeText(MyApplication.getAppContext(), "single select", Toast.LENGTH_SHORT).show()
        return super.onSingleTapConfirmed(e)
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return super.onDoubleTapEvent(e)
    }

    fun getIsSelected(): MutableLiveData<Boolean> {
        return isSelected
    }
}
