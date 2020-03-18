package com.example.twofragmentactivity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import java.io.File
import java.io.IOException


interface MoviesDataRepository<T> {
 fun getMoviesList() : MutableLiveData<List<T>>
}

class MoviesDataRepositoryImpl (val context: Context?): MoviesDataRepository<MoviesListResponse>
{
    override fun getMoviesList(): MutableLiveData<List<MoviesListResponse>> {
        val jsonFileString = getJsonDataFromAsset(context, "MovieApi.json")
        val gson = GsonBuilder().create()
        val moviesList = gson.fromJson(jsonFileString, MoviesResponse::class.java)
        val listData = MutableLiveData<List<MoviesListResponse>>()
        listData.postValue(moviesList.results)
        return listData
    }

    fun getJsonDataFromAsset(context: Context?, fileName: String): String? {
        val jsonString: String?
        try {
            jsonString = context?.assets?.open(fileName)?.bufferedReader().use { it?.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}