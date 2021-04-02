package com.osman.PrayerReminder

import com.osman.PrayerReminder.model.*
import okhttp3.internal.immutableListOf

object TestHelper {

    fun fakeResponse(date1: String, date2: String = date1, date3: String = date1, date4: String = date1): Response {
        return Response(200, "Ok",
                immutableListOf(Data(
                        Timings("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48",
                                "null",
                                "null"),
                        Date(date1, "", Gregorian(date1), Hijri(date1))
                ), Data(
                        Timings("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48",
                                "null",
                                "null"),
                        Date(date2, "", Gregorian(date2), Hijri(date2))
                ), Data(
                        Timings("03:48",
                                "06:20",
                                "12:52",
                                "16:21",
                                "19:28",
                                "21:48",
                                "null",
                                "null"),
                        Date(date3, "", Gregorian(date3), Hijri(date3))
                ))
        )
    }
}