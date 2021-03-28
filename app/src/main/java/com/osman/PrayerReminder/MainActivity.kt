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
        setContentView(R.layout.activity_main)
       subscribeObeservers()

        observOntimeclick()
    }

    private fun observOntimeclick() {
        viewModel.timeLeft.observe(this, { it ->
            findViewById<TextView>(R.id.count_down_timer).text = it
        })
    }

    private fun subscribeObeservers(){
        viewModel.getTimmings().observe(this,  Observer { datastate ->
            when(datastate){
                is DataState.Sucsess<String> -> {

                }
                is DataState.Error -> {
                    log.warning("error msg  ${datastate.msg}")
                }
            }
        })
    }


}