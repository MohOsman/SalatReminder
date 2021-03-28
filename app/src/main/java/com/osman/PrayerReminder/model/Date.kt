package com.osman.PrayerReminder.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Date(
        val readable: String,
        val timestamp: String,
        val gregorian : Gregorian,
        val hijri: Hijri
){}
data  class Gregorian(
        val date : String
)


data class Hijri(
        val date : String,

){}







/**
 * eadable": "04 Mar 2021",


"hijri": {

"date": "20-07-1442",
"format": "DD-MM-YYYY",
"day": "20",
"weekday": {
"en": "Al Khamees",
"ar": "الخميس"
},
"month": {
"number": 7,
"en": "Rajab",
"ar": "رَجَب"
},
"year": "1442",
"designation": {
"abbreviated": "AH",
"expanded": "Anno Hegirae"
},
"holidays": [ ]

},
 */

