package com.example.twofragmentactivity

import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

@Suppress("DEPRECATION")
@Database(entities = [MoviesListResponse::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: databaseBuilder(
                        MyApplication.getAppContext(),
                        AppDatabase::class.java, "moviesDb"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(CALLBACK)
                        .build()
                        .also { instance = it }
            }
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                val dao = instance?.movieDao()
                PopulateDbAsync(dao).execute()
            }
        }
    }
}

private class PopulateDbAsync(val movieDao: MovieDao?) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        val queue: RequestQueue = Volley.newRequestQueue(MyApplication.getAppContext())
        val url =
            "https://api.themoviedb.org/3/trending/all/day?api_key=008766d2113430a3a0896883b18ea254"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val gson = GsonBuilder().create()
                val moviesList = gson.fromJson(response.toString(), MoviesResponse::class.java)
                movieDao?.clear()
                movieDao?.insertAll(moviesList.results)
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })
        queue.add(request)

        return null
    }

}