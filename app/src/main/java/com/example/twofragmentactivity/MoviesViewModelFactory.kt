package com.example.twofragmentactivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            val repo = MoviesDataRepository()
            return MoviesListViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class type")
    }
}