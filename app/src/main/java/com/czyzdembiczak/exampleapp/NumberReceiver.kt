package com.czyzdembiczak.exampleapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundles = intent.extras
        val username = bundles?.getString(MainActivity.EXTRA_USERNAME)
        val number = bundles?.getInt(MainActivity.EXTRA_NUMBER)

        Log.d(this.javaClass.simpleName, "USERNAME: ${username}, NUMBER: $number")
    }
}