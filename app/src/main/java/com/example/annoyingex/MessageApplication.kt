package com.example.annoyingex

import android.app.Application
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.util.*

class MessageApplication: Application() {
    private val messageURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
    var track = hashSetOf<String>()
    var randomString = ""

    lateinit var annoyingExManager: AnnoyingExManager
        private set

    lateinit var messageNotificationManager: MessageNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()

        annoyingExManager = AnnoyingExManager(this)
        messageNotificationManager = MessageNotificationManager(this)
        fetchData()
    }

    fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, messageURL, Response.Listener {
                response ->
            //Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
            val gson = Gson()
            var messages = gson.fromJson(response, Message::class.java)
            for (message in messages.messages) {
                track.add(message) //sorry for putting the same code twice but im lazy
                val size: Int = track.size
                val item: Int = Random().nextInt(size)
                var i = 0
                for (obj in track) {
                    if (i == item) {
                        randomString = obj
                        break
                    }
                    i++
                }
            }
        }, Response.ErrorListener {
            randomString = "HAHAH NO KAREN TODAY"
        })
        queue.add(req)
    }

    fun chooseRandom() {
        val size: Int = track.size
        val item: Int = Random().nextInt(size)
        var i = 0
        for (obj in track) {
            if (i == item) {
                randomString = obj
                break
            }
            i++
        }
    }
}
