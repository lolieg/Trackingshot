package gg.norisk.trackingshot

import gg.norisk.trackingshot.animation.CameraAnimation
import kotlinx.coroutines.Runnable

class Segment(
    val position: Int
    ) : Runnable {
    val animations: MutableList<CameraAnimation> = mutableListOf()

    override fun run() {
        TODO("Not yet implemented")
    }
}