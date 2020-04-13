package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MoviesDataRepository(
    private val movieDao: MovieDao,
    private val appExecutors: AppExecutors
) {

    fun loadUser(): LiveData<Resource<List<MoviesListResponse>>> {
        return object :
            NetworkBoundResource<List<MoviesListResponse>, List<MoviesListResponse>>(appExecutors) {
            override fun saveCallResult(item: List<MoviesListResponse>) {
                movieDao.insertAll(item)
            }

            override fun shouldFetch(data: List<MoviesListResponse>?) = data?.size == 0 || data == null

            override fun loadFromDb() = movieDao.getAllMovies()

            override fun createCall() = refreshMoviesList()
        }.asLiveData()
    }

    private fun refreshMoviesList(): LiveData<ApiResponse<List<MoviesListResponse>>> {
        val movies = MutableLiveData<ApiResponse<List<MoviesListResponse>>>()

        val queue: RequestQueue = Volley.newRequestQueue(MyApplication.getAppContext())
        val url =
            "https://api.themoviedb.org/3/trending/all/day?api_key=008766d2113430a3a0896883b18ea254"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val gson = GsonBuilder().create()
                val moviesList = gson.fromJson(response.toString(), MoviesResponse::class.java)
                movies.value = ApiSuccessResponse(moviesList.results)
            }, Response.ErrorListener { error ->
                error.printStackTrace()
                movies.value = ApiErrorResponse(error.message)
            })
        queue.add(request)
        return movies
    }

    fun refreshMovies() {
        object :
            NetworkBoundResource<List<MoviesListResponse>, List<MoviesListResponse>>(appExecutors) {
            override fun saveCallResult(item: List<MoviesListResponse>) {
                movieDao.insertAll(item)
            }

            override fun shouldFetch(data: List<MoviesListResponse>?) = true

            override fun loadFromDb() = movieDao.getAllMovies()

            override fun createCall() = refreshMoviesList()
        }
    }
}