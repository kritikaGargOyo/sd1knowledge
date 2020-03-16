package com.example.twofragmentactivity

import android.content.Context
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import org.json.JSONException

class MoviesListInteractor(val context: Context?) {

    fun getMovies(moviesListResponseListener: MoviesListResponseListener) {
        val queue: RequestQueue = Volley.newRequestQueue(context)
        val url = "https://api.themoviedb.org/3/trending/all/day?api_key=008766d2113430a3a0896883b18ea254"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val jsonArray = response.getJSONArray("results")
                    val gson = GsonBuilder().create()

                    for (i in 0 until jsonArray.length()) {
                        val movie = jsonArray.getJSONObject(i)
                        val responseObj:MoviesListResponse = gson.fromJson(movie.toString(), MoviesListResponse::class.java)
                        moviesListResponseListener.onMoviesListResponse(responseObj)

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                    error -> error.printStackTrace()
            })
        queue.add(request)
    }

    interface MoviesListResponseListener {
        fun onMoviesListResponse(response: MoviesListResponse)
        fun onError(errorMessage: String?)
    }
}

