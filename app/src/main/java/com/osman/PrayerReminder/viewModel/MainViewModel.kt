package com.osman.PrayerReminder.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osman.PrayerReminder.Utils
import com.osman.PrayerReminder.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(val mainRepository: MainRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val log: Logger = Logger.getAnonymousLogger() // TODO replace this shit logger :D

    val timeLeft = MutableLiveData<String?>()
    val nextPrayer = MutableLiveData<String>()
    val nextPrayerLabel = MutableLiveData<String>()

    init {
        countDownTimer()
    }

    fun countDownTimer() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val timeAndLabel = mainRepository.getNextPrayerTime(Utils.getCurrentDate(), Utils.getCurrentTime()).split("#")
                nextPrayer.value = timeAndLabel[1].split(" ")[1].substring(0, 5)
                nextPrayerLabel.value = timeAndLabel[0]
                // TODO  refactor this line, maybe to own func?
                object : CountDownTimer(Math.abs((Utils.getTimeDiff(Utils.getCurrentDate().plus(" ".plus(Utils.getCurrentTime())), timeAndLabel[1]))), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        timeLeft.value = Utils.formatMilliSecondsToTime(millisUntilFinished)
                    }

                    override fun onFinish() {
                        this.start();
                    }
                }.start()
            } catch (e: Exception) {
                log.warning(e.toString())
            }
        }

    }


}



