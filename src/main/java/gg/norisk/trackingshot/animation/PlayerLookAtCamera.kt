package gg.norisk.trackingshot.animation

import gg.norisk.trackingshot.utils.getFreeCamera
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.silkCoroutineScope
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class PlayerLookAtCamera(val player: ClientPlayerEntity, val duration: Duration): SegmentObject {
    @OptIn(DelicateSilkApi::class)
    override suspend fun run(): Job {
        val startTime = System.nanoTime()
        return silkCoroutineScope.launch {
            val camera = getFreeCamera() ?: return@launch
            while (duration.toJavaDuration().toNanos() > System.nanoTime() - startTime) {
                yield()
                player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, camera.eyePos)
            }
        }
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}