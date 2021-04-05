package com.osman.PrayerReminder

import com.osman.PrayerReminder.model.Timings
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun formatMilliSecondsToTime(milliseconds: Long): String {
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

    fun getTimeDiff(endtime: String, startTime: String): Long {
        val endTimeParsed = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).parse(endtime)?.time!!
        val startTimeParsed = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).parse(startTime)?.time!!
        return endTimeParsed - startTimeParsed
    }

    fun getCurrentDate(): String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
    fun getCurrentTime() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    fun getYear() = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
    fun getMonth() = SimpleDateFormat("MM", Locale.getDefault()).format(Date())
    fun formatedTime(currentDate: String?, currentTime: String?): Date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
            Locale.getDefault()).parse(currentDate.plus(" ".plus(currentTime)))!!

    fun formatedTime(time: String?): Date = SimpleDateFormat("HH:mm:ss",
            Locale.getDefault()).parse(time!!)!!

    fun calculateTimeDiff(time: String) =
            Math.abs((TimeUtil.getTimeDiff(TimeUtil.getCurrentDate().plus(" ".plus(TimeUtil.getCurrentTime())), time)))

    fun getDiffTimeInSeconds(milliseconds: Long): Long = (milliseconds / 1000)

    fun convertRemainingTimeToPrecent(seconds: Long): Long {
        return seconds / 100
    }


}

object GeneralUtil {

    fun TimesToMap(timmings: Timings?): MutableMap<String, String?> {
        val prayerTimeList = mutableMapOf<String, String?>()
        prayerTimeList.put("Fajr", timmings?.fajer)
        prayerTimeList.put("Sunrise", timmings?.sunrise)
        prayerTimeList.put("Dhuhr", timmings?.dhuhr)
        prayerTimeList.put("Asr", timmings?.asr)
        prayerTimeList.put("Magrib", timmings?.maghrib)
        prayerTimeList.put("Isha", timmings?.isha)
        return prayerTimeList
    }


}









