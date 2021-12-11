package com.czyzdembiczak.exampleapp

import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.*

class CountService : IntentService("CountService") {

    init{
        instance = this
    }

    companion object{
        private lateinit var instance: CountService
        var isRunning = false

        fun stopService(){
            Log.d("CountService","Service został zatrzymany")
            isRunning = false
            instance.stopSelf()
        }
    }

     fun counting(): Deferred<Unit> {
        return GlobalScope.async(Dispatchers.IO){
            try{
                isRunning = true
                var number:Int = 0

                while(isRunning){
                    number += 1
                    Log.d("CountService","$number")
                    Thread.sleep(1000)
                }
            }catch(e: InterruptedException){
                Thread.currentThread().interrupt()
            }
        }
    }


    override fun onHandleIntent(p0: Intent?) {
        counting()
    }
}