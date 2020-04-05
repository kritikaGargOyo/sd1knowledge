package com.example.twofragmentactivity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
@Entity(tableName = "movies")
data class MoviesListResponse(
    var adult: Boolean?,
    var backdrop_path: String?,
    @PrimaryKey
    var id: Int,
    var original_language: String?,
    var original_title: String?,
    var overview: String?,
    var poster_path: String?,
    var release_date: String?,

    var title: String?,
    var video: Boolean?,
    var vote_average: Double?,
    var vote_count: Int?,
    var popularity: Double?,
    var isLiked:Boolean
) : Parcelable, Serializable
