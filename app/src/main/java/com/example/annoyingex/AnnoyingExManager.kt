package com.example.annoyingex

import android.content.Context
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

class AnnoyingExManager(private val context: Context) {

    private  var workManager: WorkManager = WorkManager.getInstance(context)

    fun startAnnoyingEx() {
        if(!isRunning()) {
            val constraints = Constraints.Builder()
                //.setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<SendAMessageWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .addTag(requestID)
                .build()
            workManager.enqueue(workRequest)
        }

    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(requestID).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(requestID)
    }

    companion object {
        const val requestID = "WORK_REQUEST_TAG"
    }


}