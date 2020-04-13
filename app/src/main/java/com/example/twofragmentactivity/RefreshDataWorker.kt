package com.example.twofragmentactivity

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RefreshDataWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "refreshData"
    }

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        val database = AppDatabase.getInstance()
        val repository = MoviesDataRepository(database.movieDao(), AppExecutors())
        repository.refreshMovies()
        return Result.Success()
    }
}