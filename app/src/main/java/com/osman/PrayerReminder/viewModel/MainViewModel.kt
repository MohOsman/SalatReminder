package com.osman.PrayerReminder.viewModel

import android.os.Build
import android.os.CountDownTimer
import android.provider.ContactsContract
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.osman.PrayerReminder.Utils
import com.osman.PrayerReminder.model.Response
import com.osman.PrayerReminder.model.Timings
import com.osman.PrayerReminder.repository.MainRepository
import com.osman.PrayerReminder.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response.error
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(val mainRepository: MainRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    lateinit var timer: CountDownTimer
    val timeLeft = MutableLiveData<String?>()
    val startTime = "11:56:00" // remove later
    val endtime = "14:58:00" // remove later
    init {
        timer = object : CountDownTimer(Utils.getTimeDiff(endtime,startTime), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.value = Utils.formatMilliSecondsToTime(millisUntilFinished)
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()
    }

    fun getTimmings() = liveData<DataState<Response>>(Dispatchers.IO) {
        emit(DataState.Loading)
        try {
            emit(DataState.Sucsess(data = mainRepository.getTimmings()))
        } catch (exception: Exception) {
            emit(DataState.Error(exception.message));
        }


    }


}


