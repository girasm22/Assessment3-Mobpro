package org.d3if4078.limassegiempat.ui.bangun_ruang

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.d3if4078.limassegiempat.MainActivity
import org.d3if4078.limassegiempat.network.UpdateWorker
import java.util.concurrent.TimeUnit

class BangunRuangViewModel: ViewModel() {
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}