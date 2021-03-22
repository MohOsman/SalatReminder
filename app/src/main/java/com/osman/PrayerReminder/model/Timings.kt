package com.osman.PrayerReminder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data  class Timings(
    @SerializedName("Fajer")
    @Expose
    val fajer : String,
    @SerializedName("Sunrise")
    @Expose
    val sunrise : String,
    @SerializedName("Dhuhr")
    @Expose
     val dhuhr : String,
    @SerializedName("Asr")
    @Expose
    val asr : String,
    @SerializedName("Maghrib")
    @Expose
    val maghrib : String,
    @SerializedName("Isha")
    @Expose
    val isha : String,
    @SerializedName("Midnight")
    @Expose
    val midnight : String,
    @SerializedName("Imsak")
    @Expose
    val imsak : String,
) {
}
