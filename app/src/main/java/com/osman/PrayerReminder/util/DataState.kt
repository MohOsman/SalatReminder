package com.osman.PrayerReminder.util

import java.lang.Exception

sealed class DataState<out R>{
    data class Sucsess<out T>  (val data : T ) : DataState<T>()
    data class Error  (val msg : String? ) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
