package com.osman.PrayerReminder

import com.osman.PrayerReminder.Mapper.PrayertimeNetworkMapper
import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.repository.MainRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    val mockApiService = Mockito.mock(ApiService::class.java)
    lateinit var mainRepository: MainRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        val mapper = PrayertimeNetworkMapper()
        mainRepository = MainRepository(mockApiService, mapper)
    }


    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `should return correct time to next prayer`() = testScope.runBlockingTest {
        Mockito.`when`(mockApiService.get()).thenReturn(TestHelper.fakeResponse("01-04-2021"))
        val actualNextPrayerTime = mainRepository.getNextPrayerTime("01-04-2021", "13:25:00")
        val expectedNextPrayerTimer = "Asr#01-04-2021 16:21:00"
        assertEquals(expectedNextPrayerTimer, actualNextPrayerTime)
    }

    @Test
    fun `should return correct time to next prayer on next Day`() = testScope.runBlockingTest {
        Mockito.`when`(mockApiService.get()).thenReturn(TestHelper.fakeResponse("01-04-2021", "02-04-2021"))
        val actualNextPrayerTime = mainRepository.getNextPrayerTime("01-04-2021", "22:22:29")
        val expectedNextPrayerTimer = "Fajr#02-04-2021 03:48:00"
        assertEquals(expectedNextPrayerTimer, actualNextPrayerTime)
    }


}