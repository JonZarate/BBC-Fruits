package com.jonzarate.bbc_fruits.view.detail

import com.jonzarate.bbc_fruits.CoroutinesTestRule
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTests {

    @get:Rule
    @ExperimentalCoroutinesApi
    val dispatcher = CoroutinesTestRule()

    private val app = mockk<AppRepository>(relaxed = true)
    private val viewmodel = DetailViewModel(app)


    @After
    fun clean () {
        confirmVerified(app)
        clearMocks(app)
    }

    @Test
    fun test_reports_analytics () {
        viewmodel.onPageLoaded()

        coVerify { app.pageLoaded(DetailFragment::class.toString()) }
    }
}