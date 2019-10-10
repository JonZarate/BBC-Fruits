package com.jonzarate.bbc_fruits.data.repo

import okhttp3.Protocol
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeCall(
    private val start: Long,
    private val end: Long,
    private val isSuccessful: Boolean
) : Call<String> {

    override fun enqueue(callback: Callback<String>) { }

    override fun isExecuted() = true

    override fun clone() = FakeCall(start, end, isSuccessful)

    override fun isCanceled() = false

    override fun cancel() { }

    override fun execute(): Response<String> =
        Response.success(
            "body",
            okhttp3.Response.Builder()
                .request(request())
                .protocol(Protocol.HTTP_2)
                .code(if (isSuccessful) 200 else 400)
                .message("")
                .sentRequestAtMillis(start)
                .receivedResponseAtMillis(end)
                .build())

    override fun request() : Request =
        Request.Builder().url("https://url.co.uk").build()
}