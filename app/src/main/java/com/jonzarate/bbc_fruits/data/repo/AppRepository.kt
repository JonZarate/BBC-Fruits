package com.jonzarate.bbc_fruits.data.repo

import com.jonzarate.bbc_fruits.data.net.AnalyticsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.lang.Exception

class AppRepository (
    private val api: AnalyticsApi
) {

    private val pages = hashMapOf<String, Long>()

    private suspend fun sendEventLoad(data: Long) = api.sendEventLoad(data)

    private suspend fun sendEventDisplay(data: Long) = api.sendEventDisplay(data)

    suspend fun sendEventError(data: String) = api.sendEventError(data)

    suspend fun <T> executeCall(call: Call<T>) : T? {
        /*
            This method executes all calls: Every time it happens, the time difference is calculated
            and another call is done to report it (analytics)
         */

        try {
            return withContext(Dispatchers.IO) {
                val response = call.execute()
                val raw = response.raw()

                val sent = raw.sentRequestAtMillis()
                val received = raw.receivedResponseAtMillis()

                sendEventLoad(received - sent)

                response.body()
            }
        } catch ( e: Exception) {
            sendEventError(e.stackTrace.toString())
        }

        return null
    }

    fun pageRequested(page: String) {
        pages[page] = System.currentTimeMillis()
    }

    suspend fun pageLoaded(page: String) {
        val end = System.currentTimeMillis()
        pages[page]?.let { start ->
            val diff = end - start
            withContext(Dispatchers.IO) {
                sendEventDisplay(diff)
            }
        }
    }
}