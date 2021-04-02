package com.osman.PrayerReminder

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun `Time diff in millis should be corect`() {
        val time1 = "01-04-2021 22:28:00"
        val time2 = "02-04-2021 03:28:00"
        val expecteddiff = "5:00:00"
        val timediff = Utils.formatMilliSecondsToTime(Utils.getTimeDiff(time2, time1))
        assertEquals("5:00:00", timediff)
    }

    @Test
    fun `should get current year`() {
        val acutalYear = Utils.getYear();
        val expectedYear = "2021"
        assertEquals(expectedYear, acutalYear)
    }

    @Test
    fun `should get current month`() {
        val actualMonth = Utils.getMonth()
        val expectedMonth = "04"
        assertEquals(expectedMonth, actualMonth)
    }
}
