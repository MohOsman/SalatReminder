package com.osman.PrayerReminder

import com.osman.PrayerReminder.dto.*
import okhttp3.internal.immutableListOf

object TestHelper {

    fun fakeResponse(date1: String, date2: String = date1, date3: String = date1, date4: String = date1): ResponseDTO {
        return ResponseDTO(200, "Ok",
                immutableListOf(DataDTO(
                        TimingsDTO("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48"),
                        Date(date1, "", Gregorian(date1), Hijri(date1))
                ), DataDTO(
                        TimingsDTO("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48"),
                        Date(date2, "", Gregorian(date2), Hijri(date2))
                ), DataDTO(
                        TimingsDTO("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48"),
                        Date(date3, "", Gregorian(date3), Hijri(date3))
                ))
        )
    }
}