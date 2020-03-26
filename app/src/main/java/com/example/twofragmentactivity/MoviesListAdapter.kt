package com.example.twofragmentactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesListAdapter( private val movieListOnClickInterface: MovieListOnClickInterface) :
    RecyclerView.Adapter<MoviesListViewHolder>() {

    var responseList: List<MoviesListResponse> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.list_item,
                viewGroup,
                false
            ) , movieListOnClickInterface
        )
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.updateData(responseList[position])
    }
}