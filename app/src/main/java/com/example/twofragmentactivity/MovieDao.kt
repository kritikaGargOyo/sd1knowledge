package com.example.twofragmentactivity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MoviesListResponse>>

    @Insert(onConflict = REPLACE)
    fun insertAll(movies: List<MoviesListResponse>)

    @Query("DELETE from movies")
    fun clear()
}