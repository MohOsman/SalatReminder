package com.osman.PrayerReminder.ApiTest

import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.TestHelper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ApiTest {

    private val filePath = "src/main/res/data/prayers.json"
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    lateinit var mockserver: MockWebServer

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockserver = MockWebServer()
        mockserver = MockWebServer()
        mockserver.start()

    }


    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }


    @Test
    fun `read sample success json file`() {
        assertNotNull(readJsonFile(filePath))
    }

    @Test
    fun `fetch data response should be 200 ok`() = testScope.runBlockingTest {
        val expectedStatusCode = 200
        val expectedStatus = "Ok"

        val actualresponse = mock<ApiService> {
            onBlocking { get() }.doReturn(TestHelper.fakeResponse("01-04-2021"))
        }.get()

        assertEquals(actualresponse.code, expectedStatusCode)
        assertEquals(actualresponse.status, expectedStatus)


    }


    fun readJsonFile(fileName: String) =
            File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)
}

