package gg.norisk.trackingshot

import kotlinx.coroutines.Runnable
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class CameraGenericAnimation(var start: Float, var end: Float, var duration: Duration, var easing: Easing, val mode: Modes): GenericAnimation(start, end, duration.toJavaDuration(), easing), Runnable {

    enum class Modes{
        ZOOM,
        MINIMUM_DISTANCE,
        YAW,
        PITCH,
        POV,
        PERSPECTIVE_LOOK_AT_FACE,
        NORMAL_CAMERA,
        ORTHO_CAMERA
    }

    override fun run() {
        this.reset()
    }
}