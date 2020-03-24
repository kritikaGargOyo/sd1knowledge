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
                        .build()
                        .also { instance = it }
            }
        }
    }
}