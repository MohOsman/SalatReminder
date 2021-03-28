package com.osman.PrayerReminder.repository

import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.Utils
import com.osman.PrayerReminder.model.Timings
import okhttp3.internal.Util
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import kotlin.collections.HashMap


class MainRepository constructor(
    private val  apiService: ApiService

){
    val log : Logger = Logger.getAnonymousLogger();

    suspend fun getTimmings() : String{
        val response = apiService.get();
        val currentDate = Utils.getCurrentDate();
        val currenTime = Utils.getCurrentTime();
        for ( data in response.data){
            if (data.date.gregorian.date == currentDate){
                val timmings = data.timings;
               return calculateNextPrayer(currenTime, data.date.gregorian.date, timmings)
            }
        }
        return "nothing from here " // TODO Handle this
    }


    // TODO refactor this code block , Too large
    private fun calculateNextPrayer(currenTime: String, prayerDate:String, timmings: Timings):String {
        val calender : Calendar = Calendar.getInstance();
        log.info("currentdate : ${Utils.getCurrentDate()} ,  prayerDAte : $prayerDate")
        calender.time = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault()).parse(Utils.getCurrentDate().plus(" ".plus(currenTime)))!!;
        log.info("calender ${calender.time}")
        val prayerTimeList = mutableMapOf<String,String>()
        prayerTimeList.put("Fajr",timmings.fajer)
        prayerTimeList.put("Duhur",timmings.dhuhr)
        prayerTimeList.put("Asr",timmings.asr)
        prayerTimeList.put("Magrib",timmings.maghrib)
        prayerTimeList.put("Isha" ,timmings.isha)
        prayerTimeList.put("Sunrise", timmings.sunrise)

        for((prayerName, prayerString) in prayerTimeList){
            val prayerCalenderInstance = Calendar.getInstance();
            val prayerTime  = prayerString.substring(0,5).plus(":00")
            prayerCalenderInstance.time = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault()).parse(prayerDate.plus(" ".plus(prayerTime)))!!;
            log.info("calender time  ${calender.time}   prayertime ${prayerCalenderInstance.time}")
            if(calender.time.after(prayerCalenderInstance.time)) {
                print("calender time  $calender   prayertime $prayerTime")
                return prayerName.plus(" ".plus(prayerTime))
            }
        }
        return "noThing"  // TODO handle this
    }





}

