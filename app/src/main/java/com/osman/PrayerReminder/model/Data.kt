package com.osman.PrayerReminder.model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("timings")
        val timings: Timings

) {

}
