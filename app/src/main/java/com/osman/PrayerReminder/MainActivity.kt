package com.osman.PrayerReminder

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.osman.PrayerReminder.model.Response
import com.osman.PrayerReminder.util.DataState
import com.osman.PrayerReminder.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private  val viewModel : MainViewModel by viewModels()

    val log : Logger = Logger.getAnonymousLogger()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        subscribeObeservers()




        setContentView(R.layout.activity_main)
        val mCountDownTextView  = findViewById<TextView>(R.id.countDown);
        val startTime = "11:56:00"
        val endtime = "14:58:00" // test as
      CountDownTimer(Utils.getTimeDiff(endtime, startTime), mCountDownTextView)


    }

    private fun subscribeObeservers(){
        viewModel.getTimmings().observe(this,  Observer { datastate ->
            when(datastate){
                is DataState.Sucsess<Response> -> {
                    log.info("data ${datastate.data}")
                }
                is DataState.Error -> {
                    log.warning("msg form the API ${datastate.msg}")
                }
            }
        })
    }

    private fun CountDownTimer(diff: Long, mCountDownTextView: TextView) {
        object : CountDownTimer(diff, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mCountDownTextView.setText(Utils.formatMilliSecondsToTime(millisUntilFinished));
            }

            override fun onFinish() {
                log.info("Done")
            }
        }.start()
    }


}