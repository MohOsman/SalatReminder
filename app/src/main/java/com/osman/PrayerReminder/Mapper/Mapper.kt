package com.osman.PrayerReminder.Mapper

interface Mapper<Input, Output> {
    fun mapToDomainModel(enity: Input): Output

}