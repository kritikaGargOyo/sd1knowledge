package com.example.twofragmentactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.twofragmentactivity.repository.NetworkState
import com.example.twofragmentactivity.vo.MoviesListResponse

class MoviesListAdapter(
    private var context: Context,
    private val movieListOnClickInterface: MovieListOnClickInterface
) :
    PagedListAdapter<MoviesListResponse, RecyclerView.ViewHolder>(MovieDiffCallback()) {

 //   var responseList: ArrayList<MoviesListResponse> = arrayListOf()

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            ), movieListOnClickInterface
        )
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MoviesListResponse>() {
        override fun areItemsTheSame(
            oldItem: MoviesListResponse,
            newItem: MoviesListResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviesListResponse,
            newItem: MoviesListResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(itemCount > 0) {
            (holder as MoviesListViewHolder).updateData(getItem(position), context)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }
}