package com.czyzdembiczak.exampleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.czyzdembiczak.exampleapp.MainActivity.Companion.RECEIVER_ACTION_USER_AND_NUMBER


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val username = intent.getStringExtra(MainActivity.EXTRA_USERNAME)

        val btnStartCount = findViewById<Button>(R.id.btnStartCount)
        btnStartCount.setOnClickListener {
            Thread {
                Intent(this, CountService::class.java).also {
                    startService(it)
                }
            }.start()
        }

        val btnStoptCount = findViewById<Button>(R.id.btnStopCount)
        btnStoptCount.setOnClickListener {
            val number = CountService.stopService()
            val intent = Intent()
            intent.action = RECEIVER_ACTION_USER_AND_NUMBER
            intent.putExtra(MainActivity.EXTRA_USERNAME, username)
            intent.putExtra(MainActivity.EXTRA_NUMBER, number)

            sendBroadcast(intent)
        }
    }
}