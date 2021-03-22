package com.osman.PrayerReminder.viewModel

import android.os.Build
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
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
constructor(val mainRepository: MainRepository,
            private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun getTimmings() = liveData<DataState<Response>>(Dispatchers.IO) {
        emit(DataState.Loading)
        try {
            emit(DataState.Sucsess(data = mainRepository.getTimmings()))
        } catch (exception: Exception) {
            emit(DataState.Error(exception.message));
        }
    }
}


