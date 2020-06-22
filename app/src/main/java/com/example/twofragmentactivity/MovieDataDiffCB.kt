package com.example.twofragmentactivity

import androidx.recyclerview.widget.DiffUtil

class MovieDataDiffCB : DiffUtil.ItemCallback<MoviesListResponse?>() {
    override fun areItemsTheSame(oldItem: MoviesListResponse, newItem: MoviesListResponse)
            : Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: MoviesListResponse, newItem: MoviesListResponse): Boolean {
        return oldItem == newItem
    }
}
