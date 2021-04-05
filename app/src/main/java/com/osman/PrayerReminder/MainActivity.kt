package com.osman.PrayerReminder

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.osman.PrayerReminder.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private  val viewModel : MainViewModel by viewModels()

    val log : Logger = Logger.getAnonymousLogger()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observOntimeclick()
        setContentView(R.layout.activity_main)
        findViewById<ProgressBar>(R.id.progress_bar).setProgress(100)
    }


    private fun observOntimeclick() {
        viewModel.timeLeft.observe(this, {
            findViewById<TextView>(R.id.count_down_timer).text = it
        })

        viewModel.nextPrayerLabel.observe(this, {
            findViewById<TextView>(R.id.prayer_label).text = it
        })
        viewModel.nextPrayer.observe(this, {
            findViewById<TextView>(R.id.time_label).text = it
        })
    }
}