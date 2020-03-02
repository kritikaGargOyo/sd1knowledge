package com.example.twofragmentactivity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class movies(

    var filterName: String? = null,
    var numFilterItems: Int? = null
//    var list : List<ApiDataInfo> = listOf()
    // var DataSet: List<FilterItemModel> = listOf()

) : Parcelable