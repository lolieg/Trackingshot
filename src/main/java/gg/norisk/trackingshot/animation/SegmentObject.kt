package gg.norisk.trackingshot.animation

import gg.norisk.trackingshot.GenericRunnable
import kotlinx.serialization.Serializable

@Serializable
sealed interface SegmentObject: GenericRunnable {
}