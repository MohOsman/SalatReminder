package com.osman.PrayerReminder.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDTO(@SerializedName("code")
                       val code: Int,
                       @SerializedName("status")
                       val status: String,
                       @SerializedName("data")
                       val data: List<DataDTO>) {

}

data class DataDTO(
        @SerializedName("timings")
        val timings: TimingsDTO?,
        @SerializedName("date")
        val date: Date?

)

data class TimingsDTO(
        @SerializedName("Fajr")
        @Expose
        val fajer: String,
        @SerializedName("Sunrise")
        @Expose
        val sunrise: String,
        @SerializedName("Dhuhr")
        @Expose
        val dhuhr: String,
        @SerializedName("Asr")
        @Expose
        val asr: String,
        @SerializedName("Maghrib")
        @Expose
        val maghrib: String,
        @SerializedName("Isha")
        @Expose
        val isha: String
)


data class Date(
        @SerializedName("readable")
        val readable: String,
        @SerializedName("timeStamp")
        val timestamp: String,
        @SerializedName("gregorian")
        val gregorian: Gregorian,
        @SerializedName("hijri")
        val hijri: Hijri
)

data class Gregorian(
        @SerializedName("date")
        val date: String)

data class Hijri(@SerializedName("date") val date: String)
