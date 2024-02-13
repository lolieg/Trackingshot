package gg.norisk.trackingshot

import gg.norisk.trackingshot.segments.Segment
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.silkCoroutineScope


@OptIn(DelicateSilkApi::class)
fun ClientPlayerEntity.startTrackingShot(trackingShot: TrackingShot): Job {
    return silkCoroutineScope.launch {
        trackingShot.run()
    }
}


class TrackingShot(val client: MinecraftClient): Runnable {
    var isDone = false
    var cancelled = false
    val jobs = mutableListOf<Job>()

    val segments = mutableListOf<Segment>()
    override fun run() {
        for (segment in segments) {
            if(cancelled)
        }
    }


}