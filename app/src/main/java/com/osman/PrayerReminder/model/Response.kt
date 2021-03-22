package com.osman.PrayerReminder.model

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code : Int,
    @SerializedName("status")
    val status  : String,
    @SerializedName("data")
    val data : List<Data>
) {

}