package com.osman.PrayerReminder.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osman.PrayerReminder.TimeUtil
import com.osman.PrayerReminder.TimeUtil.calculateTimeDiff
import com.osman.PrayerReminder.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(val mainRepository: MainRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val timeLeft = MutableLiveData<String?>()
    val nextPrayer = MutableLiveData<String>()
    val nextPrayerLabel = MutableLiveData<String>()
    val progressBarValue = MutableLiveData<Int>()

    init {
        countDownTimer()
    }
    fun countDownTimer() {
        viewModelScope.launch {
            val timeAndLabel = mainRepository.getNextPrayerTime(TimeUtil.getCurrentDate(), TimeUtil.getCurrentTime())
                    .split("#")
            nextPrayer.value = timeAndLabel[1].split(" ")[1].substring(0, 5)
            nextPrayerLabel.value = timeAndLabel[0]

            object : CountDownTimer(calculateTimeDiff(timeAndLabel[1]), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeft.value = TimeUtil.formatMilliSecondsToTime(millisUntilFinished)
                }

                override fun onFinish() {
                    this.start();
                }
            }.start()

        }
    }


}



