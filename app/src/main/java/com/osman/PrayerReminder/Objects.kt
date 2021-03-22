package com.osman.PrayerReminder

import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

object Utils{

    fun formatMilliSecondsToTime(milliseconds: Long): String? {
        val seconds = (milliseconds / 1000).toInt() % 60
        val minutes = (milliseconds / (1000 * 60) % 60).toInt()
        val hours = (milliseconds / (1000 * 60 * 60) % 24).toInt()
        return (twoDigitString(hours.toLong()) + ":" + twoDigitString(minutes.toLong()) + ":"
                + twoDigitString(seconds.toLong()))
    }

    private fun twoDigitString(number: Long): String {
        if (number == 0L) {
            return "00"
        }
        return if (number / 10 == 0L) {
            "0$number"
        } else number.toString()
    }

     fun getTimeDiff(endtime: String, startTime: String) =
         SimpleDateFormat(
                 "HH:mm:ss", Locale.ENGLISH).parse(endtime)?.time!!- SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
                 .parse(startTime)?.time!!

    }







