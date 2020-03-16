package com.example.twofragmentactivity.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
data class MoviesListResponse(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var genre_ids: List<Int> = listOf(),
    var id: Int? = null,
    var original_language: String? = null,
    var original_title: String = "",
    var overview: String? = null,
    var poster_path: String = "",
    var release_date: String? = null,
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Float? = null,
    var vote_count: Int? = null,
    var popularity: Float? = null,
    var media_type: String? = null
) : Parcelable, Serializable
