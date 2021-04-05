package com.osman.PrayerReminder.repository

import com.osman.PrayerReminder.GeneralUtil.TimesToMap
import com.osman.PrayerReminder.Mapper.PrayertimeNetworkMapper
import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.TimeUtil
import com.osman.PrayerReminder.model.Prayertime
import com.osman.PrayerReminder.model.Timings
import java.util.*


class MainRepository constructor(
        private val apiService: ApiService,
        private val mapper: PrayertimeNetworkMapper

) {

    suspend fun getPrayertimes(): List<Prayertime> = mapper.mapToDomainModel(apiService.get())

    suspend fun getNextPrayerTime(currentDate: String, currentTime: String) = getPrayerTime(getPrayertimes(), currentDate, currentTime)

    private fun getPrayerTime(prayertimes: List<Prayertime>, currentDate: String, currentTime: String): String {
        var nextprayerTime = ""
        for (i in prayertimes.indices) {
            if (prayertimes[i].date == currentDate && timePassedIsha(prayertimes[i], currentTime)) {
                nextprayerTime = calculateNextPrayer(currentTime, currentDate, prayertimes[i + 1].date, prayertimes[i + 1].timings)
                break
            } else if (prayertimes[i].date.equals(currentDate)) {
                nextprayerTime = calculateNextPrayer(currentTime, prayertimes[i].date, currentDate, prayertimes[i].timings)
                break
            }
        }
        return nextprayerTime
    }

    private fun timePassedIsha(prayerTimes: Prayertime?, currentTime: String): Boolean {
        val ishaTime = Calendar.getInstance();
        val currentTimeInstance = Calendar.getInstance();
        ishaTime.time = TimeUtil.formatedTime(prayerTimes?.timings?.isha?.substring(0, 5).plus(":00"))
        currentTimeInstance.time = TimeUtil.formatedTime(currentTime)
        return (currentTimeInstance.after(ishaTime))
    }

    private fun calculateNextPrayer(currenTime: String?, currentDate: String?, prayerDate: String?, timmings: Timings?): String {
        var calculatedNextPrayer = ""
        val currentTimeCalender: Calendar = Calendar.getInstance()
        currentTimeCalender.time = TimeUtil.formatedTime(currentDate, currenTime)
        val prayerTimeList = TimesToMap(timmings)
        for ((prayerName, prayerString) in prayerTimeList) {
            val prayerCalenderInstance = Calendar.getInstance()
            val prayerTime = prayerString?.substring(0, 5).plus(":00")
            prayerCalenderInstance.time = TimeUtil.formatedTime(prayerDate, prayerTime)
            if (isAfterPrayer(currentTimeCalender, prayerCalenderInstance)) {
                calculatedNextPrayer = prayerName.plus("#").plus(prayerDate.plus(" ").plus(prayerTime))
                break
            }
        }
        return calculatedNextPrayer
    }

    private fun isAfterPrayer(currentTimeCalender: Calendar, prayerCalenderInstance: Calendar) = (currentTimeCalender.time.before(prayerCalenderInstance.time))

}

