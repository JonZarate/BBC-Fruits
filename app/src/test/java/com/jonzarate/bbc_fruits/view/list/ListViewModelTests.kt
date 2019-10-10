package com.jonzarate.bbc_fruits.view.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jonzarate.bbc_fruits.CoroutinesTestRule
import com.jonzarate.bbc_fruits.data.model.Fruit
import com.jonzarate.bbc_fruits.data.model.FruitResponse
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import com.jonzarate.bbc_fruits.data.repo.FruitRepository
import com.jonzarate.bbc_fruits.view.detail.DetailFragment
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ListViewModelTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    val dispatcher = CoroutinesTestRule()

    private val repo = mockk<FruitRepository>(relaxed = true)
    private val app = mockk<AppRepository>(relaxed = true)
    private val viewmodel = ListViewModel(repo, app)

    private val TYPE = "orange"
    private val PRICE = 119
    private val WEIGHT = 1000
    private val fruit = Fruit(TYPE, PRICE, WEIGHT)
    private val response = FruitResponse(ArrayList<Fruit>().apply { add(fruit) })

    @Before
    fun setup () {
        clearMocks(repo, app)
        coEvery { repo.getFruits() } coAnswers { response }
    }

    @Test
    fun test_loads_on_start () {
        coVerify { repo.getFruits() }
        assertEquals(fruit, viewmodel.fruits.value?.get(0))
        assertFalse(viewmodel.isLoading.value!!)
    }

    @Test
    fun test_selecting_fruit_sends_analytics() {
        viewmodel.onFruitSelected(fruit)

        assertEquals(fruit, viewmodel.launchDetailView.value)
        verify { app.pageRequested(DetailFragment::class.toString()) }
    }
}