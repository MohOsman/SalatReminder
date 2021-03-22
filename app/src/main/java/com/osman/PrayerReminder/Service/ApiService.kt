package com.osman.PrayerReminder.Service

import com.osman.PrayerReminder.model.Response
import com.osman.PrayerReminder.model.Timings
import retrofit2.http.GET

interface ApiService {

    @GET("calendarByCity?city=Stockholm&country=Sweden&method=3&month=03&year=2021")
    suspend fun get(): Response
}