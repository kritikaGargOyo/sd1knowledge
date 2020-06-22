package com.example.twofragmentactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MoviesListFragment()
        fragmentTransaction.replace(R.id.movies_fragment_container, fragment)
       // fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()

        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupWork()
        }
    }

    private fun setupWork() {
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(RefreshDataWorker.WORK_NAME , ExistingPeriodicWorkPolicy.KEEP,repeatingRequest)
    }
}