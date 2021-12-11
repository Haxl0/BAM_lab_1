package com.czyzdembiczak.exampleapp

import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CountService : IntentService("CountService") {

    init {
        instance = this
    }

    companion object {
        var number: Int = 0
        private lateinit var instance: CountService
        var isRunning = false

        fun stopService(): Int {
            Log.d("CountService", "Service został zatrzymany")
            isRunning = false
            instance.stopSelf()
            return number
        }
    }

    fun counting(): Deferred<Unit> {
        return GlobalScope.async(Dispatchers.IO) {
            try {
                isRunning = true

                while (isRunning) {
                    number += 1
                    Log.d("CountService", "$number")
                    Thread.sleep(1000)
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }


    override fun onHandleIntent(p0: Intent?) {
        counting()
    }
}