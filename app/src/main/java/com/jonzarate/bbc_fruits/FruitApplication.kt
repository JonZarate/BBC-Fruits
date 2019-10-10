package com.jonzarate.bbc_fruits

import android.app.Application
import kotlinx.coroutines.runBlocking

class FruitApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            try {
                runBlocking {
                    val data = paramThrowable.stackTrace.toString()
                    Injector.getAppRepository().sendEventError(data)
                }
            } catch (e: Exception) { }
        }
    }
}