package com.osman.PrayerReminder.Service

import com.osman.PrayerReminder.TimeUtil
import com.osman.PrayerReminder.dto.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // city=Stockholm&country=Sweden&method=3&month=03&year=2021"
    @GET("calendarByCity")
    suspend fun get(@Query("city") city: String = "Stockholm",
                    @Query("country") country: String = "Sweden",
                    @Query("method") method: String = "3",
                    @Query("month") month: String = TimeUtil.getMonth(),
                    @Query("year") year: String = TimeUtil.getYear()): ResponseDTO
}
