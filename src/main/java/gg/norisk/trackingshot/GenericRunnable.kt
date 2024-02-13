package gg.norisk.trackingshot

import kotlinx.coroutines.Job

interface GenericRunnable {
    suspend fun run(): Job?
    fun cancel()
}