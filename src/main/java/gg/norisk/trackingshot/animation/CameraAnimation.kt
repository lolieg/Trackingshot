package gg.norisk.trackingshot.animation

import gg.norisk.trackingshot.freecam.FreeCamera.CameraValueType
import gg.norisk.trackingshot.utils.getFreeCamera
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import net.minecraft.client.network.ClientPlayerEntity
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.silkCoroutineScope
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class CameraAnimation(var start: (player: ClientPlayerEntity) -> Float, var end: (player: ClientPlayerEntity) -> Float, var duration: Duration, var easing: Easing, val cameraValueType: CameraValueType): Animation(start, end, duration.toJavaDuration(), easing), SegmentObject {


    @OptIn(DelicateSilkApi::class)
    override suspend fun run(): Job {

        return silkCoroutineScope.launch {
            this@CameraAnimation.start()
            while(!this@CameraAnimation.isDone) {
                yield()
                val camera = getFreeCamera() ?: return@launch
                when(cameraValueType) {
                    CameraValueType.ZOOM -> camera.zoom = this@CameraAnimation.get()
                    CameraValueType.MINIMUM_DISTANCE -> camera.zNear = this@CameraAnimation.get()
                    CameraValueType.YAW -> camera.yaw = this@CameraAnimation.get()
                    CameraValueType.PITCH -> camera.pitch = this@CameraAnimation.get()
                    CameraValueType.X_COORDINATE -> camera.x = this@CameraAnimation.get().toDouble()
                    CameraValueType.Y_COORDINATE -> camera.y = this@CameraAnimation.get().toDouble()
                    CameraValueType.Z_COORDINATE -> camera.z = this@CameraAnimation.get().toDouble()
                    else -> continue
                }
            }
        }
    }

    override fun cancel() {

    }
}