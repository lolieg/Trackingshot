package gg.norisk.trackingshot

import gg.norisk.trackingshot.freecam.FreeCamera
import gg.norisk.trackingshot.segments.Segment
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.silkCoroutineScope
import kotlin.time.Duration.Companion.microseconds


fun ClientPlayerEntity.startTrackingShot(trackingShot: TrackingShot): Job {
    return trackingShot.start(this)
}

fun ClientPlayerEntity.exitTrackingShot(trackingShot: TrackingShot) {
    trackingShot.exit()
}

class TrackingShot(val segments: MutableList<Segment>, val autoClose: Boolean) {
    var cancelled = false
    var isDone = false
    var closed = false
    var cameraEntity: FreeCamera? = null
    @OptIn(DelicateSilkApi::class)
    private fun run(player: ClientPlayerEntity): Job {
        onStart(player)

        return silkCoroutineScope.launch {
            for (segment in segments) {
                if(cancelled) break
                segment.run()
                while(!segment.isDone()){
                    yield()
                    delay(50.microseconds)
                }
            }
            if(autoClose) {
                exit()
            }

            isDone = true
        }
    }

    fun start(player: ClientPlayerEntity): Job {
        return run(player)
    }

    private fun onStart(player: ClientPlayerEntity) {
        MinecraftClient.getInstance().worldRenderer.chunkBuilder.cameraPosition
        MinecraftClient.getInstance().chunkCullingEnabled = false
        cameraEntity = FreeCamera(-420)
        cameraEntity!!.setPosition(player.pos)
        cameraEntity!!.yaw = player.yaw
        cameraEntity!!.pitch = player.pitch
        cameraEntity!!.spawn()
        MinecraftClient.getInstance().gameRenderer.setRenderHand(false)
        MinecraftClient.getInstance().setCameraEntity(cameraEntity)
    }


    fun exit() {
        MinecraftClient.getInstance().gameRenderer.setRenderHand(true)
        MinecraftClient.getInstance().setCameraEntity(MinecraftClient.getInstance().player)
        cameraEntity!!.despawn()
        cameraEntity = null
        MinecraftClient.getInstance().chunkCullingEnabled = true
        closed = true

    }
    fun cancel() {
        cancelled = true
        for (segment in segments) {
            utils.cancelJobs(segment.jobs)
        }
    }

}