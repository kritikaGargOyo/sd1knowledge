package com.example.twofragmentactivity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.twofragmentactivity.vo.MoviesListResponse

class MoviesActivityNavigator(val activity: FragmentActivity?) {
    fun openDetailFragment(moviesListResponse: MoviesListResponse?){
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = MoviesDetailFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("response" , moviesListResponse)
        }
        fragmentTransaction?.add(R.id.movies_fragment_container, fragment)
        fragmentTransaction?.addToBackStack(fragment.tag)
        fragmentTransaction?.commit()

    }

}
