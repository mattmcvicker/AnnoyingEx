package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val annoyingEx = (application as MessageApplication)
        val yeet = (applicationContext as MessageApplication)
        annoyingEx.fetchData()
        btnStart.setOnClickListener {
            annoyingEx.annoyingExManager.startAnnoyingEx()
            annoyingEx.messageNotificationManager.postNotification()
            annoyingEx.chooseRandom()
        }
        btnAnnoying.setOnClickListener {
            annoyingEx.annoyingExManager.stopWork()
        }
    }



}
