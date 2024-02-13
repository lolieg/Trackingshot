package gg.norisk.trackingshot

import gg.norisk.trackingshot.client.TrackingShotClient
import gg.norisk.trackingshot.freecam.FreeCamera
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import net.silkmc.silk.core.task.mcClientCoroutineScope

object utils {

    fun cancelJobs(jobs: List<Job>) {
        for(job in jobs) {
            if(job.isActive){
                mcClientCoroutineScope.launch {
                    job.cancelAndJoin()
                }
            }
        }
    }

    fun getFreeCamera(): FreeCamera? {
        return TrackingShotClient.trackingShot?.cameraEntity
    }
}