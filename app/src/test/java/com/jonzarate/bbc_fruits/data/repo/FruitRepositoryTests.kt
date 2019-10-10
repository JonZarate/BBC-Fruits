package com.jonzarate.bbc_fruits.data.repo

import com.jonzarate.bbc_fruits.data.model.FruitResponse
import com.jonzarate.bbc_fruits.data.net.FruitsApi
import io.mockk.clearMocks
import io.mockk.coVerifyAll
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FruitRepositoryTests {

    private val api = mockk<FruitsApi>(relaxed = true)
    private val app = mockk<AppRepository>(relaxed = true)
    private val repo = FruitRepository(api, app)

    @Before
    fun setup() {
        clearMocks(api, app)
    }

    @Test
    fun test_download_fruits() {
        runBlocking { repo.getFruits() }

        coVerifyAll { api.getFruits(); app.executeCall<FruitResponse>(any()) }

        confirmVerified(api, app)
    }
}