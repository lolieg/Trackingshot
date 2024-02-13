package gg.norisk.trackingshot.animation

import gg.norisk.trackingshot.utils.getFreeCamera
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.Entity
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.silkCoroutineScope
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class LookAtEntity(val entity: Entity, val duration: Duration): SegmentObject {

    @OptIn(DelicateSilkApi::class)
    override suspend fun run(): Job {
        val startTime = System.nanoTime()
        return silkCoroutineScope.launch {
            val camera = getFreeCamera() ?: return@launch
            while (duration.toJavaDuration().toNanos() > System.nanoTime() - startTime) {

                yield()
                camera.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entity.eyePos)
            }
        }
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}