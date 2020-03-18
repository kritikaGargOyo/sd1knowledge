package com.example.twofragmentactivity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class MoviesResponse(
    var page: Int,
    var results: List<MoviesListResponse>,
    var total_pages: Int,
    var total_results: Int
) : Parcelable, Serializable
