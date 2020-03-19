package com.example.twofragmentactivity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
data class MoviesListResponse(
    var adult: Boolean,
    var backdrop_path: String?,
    var genre_ids: List<Int>,
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var poster_path: String?,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Float,
    var vote_count: Int,
    var popularity: Float
) : Parcelable, Serializable
