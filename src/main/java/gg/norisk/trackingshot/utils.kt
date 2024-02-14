package gg.norisk.trackingshot

import gg.norisk.trackingshot.client.TrackingShotClient
import gg.norisk.trackingshot.freecam.FreeCamera
import kotlinx.coroutines.Job

object utils {

    fun cancelJobs(jobs: List<Job>) {
        for(job in jobs) {
            if(job.isActive){
                job.cancel()
            }
        }
    }

    fun getFreeCamera(): FreeCamera? {
        return TrackingShotClient.trackingShot?.cameraEntity
    }
}