package com.osman.PrayerReminder.Mapper

import com.osman.PrayerReminder.dto.ResponseDTO
import com.osman.PrayerReminder.dto.TimingsDTO
import com.osman.PrayerReminder.model.Prayertime
import com.osman.PrayerReminder.model.Timings

class PrayertimeNetworkMapper : Mapper<ResponseDTO, List<Prayertime>> {
    override fun mapToDomainModel(enity: ResponseDTO): List<Prayertime> {
        return enity.data.map {
            Prayertime(timings = mapTimings(it.timings), date = it.date?.gregorian?.date)
        }
    }

    private fun mapTimings(timings: TimingsDTO?) = Timings(fajer = timings?.fajer,
            dhuhr = timings?.dhuhr,
            asr = timings?.asr,
            maghrib = timings?.maghrib,
            isha = timings?.isha,
            sunrise = timings?.sunrise)
}



