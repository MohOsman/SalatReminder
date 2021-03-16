package com.osman.PrayerReminder

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val log : Logger = Logger.getAnonymousLogger();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mCountDownTextView  = findViewById<TextView>(R.id.countDown);

        object : CountDownTimer(1800000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mCountDownTextView.setText(formatMilliSecondsToTime(millisUntilFinished));
            }

            override fun onFinish() {
                // TODO it should count to next prayer.
               log.info("Done")
            }
        }.start()


    }

     // TODO: this function needs to refactored and polished. maybe move to own service class ?

    private fun formatMilliSecondsToTime(milliseconds: Long): String? {
        val seconds = (milliseconds / 1000).toInt() % 60
        val minutes = (milliseconds / (1000 * 60) % 60).toInt()
        val hours = (milliseconds / (1000 * 60 * 60) % 24).toInt()
        return (twoDigitString(hours.toLong()) + ":" + twoDigitString(minutes.toLong()) + ":"
                + twoDigitString(seconds.toLong()))
    }

    private fun twoDigitString(number: Long): String {
        if (number == 0L) {
            return "00"
        }
        return if (number / 10 == 0L) {
            "0$number"
        } else number.toString()
    }

}