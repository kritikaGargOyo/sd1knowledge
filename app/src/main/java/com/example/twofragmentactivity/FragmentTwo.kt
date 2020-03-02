package com.example.twofragmentactivity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_two.*
import org.json.JSONException

class FragmentTwo(): AppCompatDialogFragment()
{
    private var rootview: View? = null
    var recyclerView: RecyclerView? = null
    var mAdapter:FragmentTwoAdapter? = null
    var movies = ArrayList<String>()
    var image = ArrayList<String>()
    private lateinit var LayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       rootview  = inflater.inflate(R.layout.fragment_two,container,false)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=rootview?.findViewById(R.id.recyclerView)
        LayoutManager = GridLayoutManager(this.context,2)
        recyclerView?.layoutManager = LayoutManager
        getMovies()
        mAdapter = FragmentTwoAdapter(movies,image,context as Context)
        recyclerView?.adapter = mAdapter

    }

    fun getMovies()
    {
        var queue: RequestQueue = Volley.newRequestQueue(context)
        val url = "https://api.themoviedb.org/3/trending/movie/week?api_key=008766d2113430a3a0896883b18ea254"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    var jsonArray = response.getJSONArray("results")
                    for (i in 0 until jsonArray.length()) {
                        val movie = jsonArray.getJSONObject(i)
                        val firstname = movie.getString("title")
                        val imageMovie = movie.getString("poster_path")
                        movies.add(firstname)
                        image.add("https://image.tmdb.org/t/p/w500"+imageMovie)
                        mAdapter?.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace()
            })
        queue.add(request)
    }
}