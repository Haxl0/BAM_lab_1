package com.czyzdembiczak.exampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        print(message)

        val textView = findViewById<TextView>(R.id.tvUserActivityView).apply {
            text = message
        }

    }
}