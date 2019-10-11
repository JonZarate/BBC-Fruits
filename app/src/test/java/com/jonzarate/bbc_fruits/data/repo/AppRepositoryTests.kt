package com.jonzarate.bbc_fruits.data.repo

import com.jonzarate.bbc_fruits.data.net.AnalyticsApi
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppRepositoryTests {

    private val api = mockk<AnalyticsApi>(relaxed = true)
    private val repo = AppRepository(api)


    @After
    fun clean () {
        confirmVerified(api)
        clearMocks(api)
    }

    @Test
    fun test_analytics_load() {
        val START = 0L
        val END = 2222L
        runBlocking {
            val call = FakeCall(START, END, true)
            repo.executeCall(call)
        }

        coVerify { api.sendEventLoad(END) }
    }

    @Test
    fun test_analytics_load_fails() {
        val START = 0L
        val END = 2222L
        runBlocking {
            val call = FakeCall(START, END, false)
            repo.executeCall(call)
        }

        coVerify { api.sendEventError(any()) }
    }

    @Test
    fun test_analytics_display() {
        runBlocking {
            val PAGE = "PageName"
            repo.pageRequested(PAGE)
            repo.pageLoaded(PAGE)
        }

        coVerify { api.sendEventDisplay(any()) }
    }

    @Test
    fun test_analytics_error() {
        val ERROR = "No internet connection"

        runBlocking {
            repo.sendEventError(ERROR)
        }

        coVerify { api.sendEventError(ERROR) }
    }
}