package com.example.twofragmentactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentTwo()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()


    }



}