package com.osman.PrayerReminder.repository

import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.model.Response
import com.osman.PrayerReminder.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.logging.Logger


class MainRepository constructor(
    private val  apiService: ApiService

){
    val log : Logger = Logger.getAnonymousLogger();

    suspend fun getTimmings() = apiService.get();

}

