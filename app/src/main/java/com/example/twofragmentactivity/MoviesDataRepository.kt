package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MoviesDataRepository {
    private var movieDao: MovieDao

    init {
        val db = AppDatabase.getInstance()
        movieDao = db.movieDao()

    }

    fun getMovies(): LiveData<List<MoviesListResponse>> {

        refreshMoviesList()
        return movieDao.getAllMovies()
    }

    private fun refreshMoviesList()
    {
        val queue: RequestQueue = Volley.newRequestQueue(MyApplication.getAppContext())
        val url =
            "https://api.themoviedb.org/3/trending/all/day?api_key=008766d2113430a3a0896883b18ea254"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val gson = GsonBuilder().create()
                val moviesList = gson.fromJson(response.toString(), MoviesResponse::class.java)
                movieDao.insertAll(moviesList.results)
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })
        queue.add(request)
    }

}