package com.example.twofragmentactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MoviesListAdapter(private val movieListOnClickInterface: MovieListOnClickInterface) :
    ListAdapter<MoviesListResponse?, RecyclerView.ViewHolder>(MovieDataDiffCB()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.list_item,
                viewGroup,
                false
            ), movieListOnClickInterface
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MoviesListViewHolder)
            holder.updateData(getItem(position))
    }
}