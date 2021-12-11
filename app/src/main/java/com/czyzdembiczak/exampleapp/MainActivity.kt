package com.czyzdembiczak.exampleapp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var receiver: BroadcastReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureReceiver()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction(RECEIVER_ACTION_USER_AND_NUMBER)
        receiver = NumberReceiver()
        registerReceiver(receiver, filter)
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.etUserName)
        val username = editText.text.toString()
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra(EXTRA_USERNAME, username)
        }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_USERNAME = "com.czyzdamian.exampleapp.USERNAME"
        const val EXTRA_NUMBER = "com.czyzdamian.exampleapp.NUMBER"

        const val RECEIVER_ACTION_USER_AND_NUMBER = "intent.usernate_and_number"
    }
}