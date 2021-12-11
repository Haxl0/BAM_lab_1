package com.czyzdembiczak.exampleapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.czyzdembiczak.exampleapp.db.User
import com.czyzdembiczak.exampleapp.db.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberReceiver(private val userRepository: UserRepository) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundles = intent.extras
        val username = bundles?.getString(MainActivity.EXTRA_USERNAME)
        val number = bundles?.getInt(MainActivity.EXTRA_NUMBER)

        Log.d(this.javaClass.simpleName, "USERNAME: ${username}, NUMBER: $number")
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.insertUser(User(username = username, number = number))
        }
    }
}