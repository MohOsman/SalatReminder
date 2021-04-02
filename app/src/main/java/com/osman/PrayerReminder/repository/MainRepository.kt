package com.osman.PrayerReminder.repository

import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.model.Data
import com.osman.PrayerReminder.model.Timings
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger


class MainRepository constructor(
        private val apiService: ApiService

) {
    val log: Logger = Logger.getAnonymousLogger();

    suspend fun getNextPrayerTime(currentDate: String, currentTime: String): String {
        val response = apiService.get()
        for (i in response.data.indices) {
            if (response.data[i].date.gregorian.date == currentDate && timePassedIsha(response.data[i], currentTime)) {
                return calculateNextPrayer(currentTime, currentDate, response.data[i + 1].date.gregorian.date, response.data[i + 1].timings)
            } else if (response.data[i].date.gregorian.date.equals(currentDate)) {
                val timings = response.data[i].timings
                return calculateNextPrayer(currentTime, response.data[i].date.gregorian.date, currentDate, timings)
            }
        }

        throw Exception("Prayer Time Not found")  // TODO mybe this is the the right  way to handle this. change in future.
    }

    private fun timePassedIsha(data: Data, currentTime: String): Boolean {
        val ishatime = Calendar.getInstance();
        val currentTimeInstance = Calendar.getInstance();
        ishatime.time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(data.timings.isha.substring(0, 5).plus(":00"))!!
        currentTimeInstance.time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(currentTime)!!
        return (currentTimeInstance.after(ishatime))

    }


    private fun calculateNextPrayer(currenTime: String, currentDate: String, prayerDate: String, timmings: Timings): String {
        val calender: Calendar = Calendar.getInstance();
        calender.time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).parse(currentDate.plus(" ".plus(currenTime)))!!;
        val prayerTimeList = TimesToMap(timmings)
        for ((prayerName, prayerString) in prayerTimeList) {
            val prayerCalenderInstance = Calendar.getInstance()
            val prayerTime = prayerString.substring(0, 5).plus(":00")
            prayerCalenderInstance.time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).parse(prayerDate.plus(" ".plus(prayerTime)))!!;
            if (calender.time.before(prayerCalenderInstance.time)) {
                log.info(prayerName.plus("-").plus(prayerDate.plus("-").plus(prayerTime)))
                return prayerName.plus("#").plus(prayerDate.plus(" ").plus(prayerTime))
            }
        }
        return "noThing"  // TODO handle this
    }

    private fun TimesToMap(timmings: Timings): MutableMap<String, String> {
        val prayerTimeList = mutableMapOf<String, String>()
        prayerTimeList.put("Fajr", timmings.fajer)
        prayerTimeList.put("Dhuhr", timmings.dhuhr)
        prayerTimeList.put("Asr", timmings.asr)
        prayerTimeList.put("Magrib", timmings.maghrib)
        prayerTimeList.put("Isha", timmings.isha)
        prayerTimeList.put("Sunrise", timmings.sunrise)
        return prayerTimeList
    }


}

