package com.example.twofragmentactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MoviesListFragment()
        fragmentTransaction.replace(R.id.movies_fragment_container, fragment)
       // fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}