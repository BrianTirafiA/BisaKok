package com.example.ranimalexe.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask

class TrackerService : Service() {

    companion object {
        var isServiceRunning = false
    }

    private var distance : Float = 0.0f

    private var counter = 0
    private val timer = Timer()

    private fun startCounter() {
        val task = object : TimerTask() {
            override fun run() {
                counter++
                println("Counter: $counter")
            }
        }
        timer.schedule(task, 0, 1000)
    }

    private fun stopCounter() {
        timer.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isServiceRunning) {
            stopSelf()
            return START_NOT_STICKY // Optionally prevent restarting
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        startCounter()
        isServiceRunning = true;
    }

    override fun onDestroy() {
        stopCounter()
        isServiceRunning = false;
    }
}