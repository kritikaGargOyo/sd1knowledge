package com.example.twofragmentactivity.vo

import com.google.gson.annotations.SerializedName

data class MoviesApiResult(
    val page: Int,
    @SerializedName("results")
    val movieList:List<MoviesListResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)