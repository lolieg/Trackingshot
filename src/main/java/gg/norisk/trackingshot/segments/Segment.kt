package gg.norisk.trackingshot.segments

import gg.norisk.trackingshot.GenericRunnable
import gg.norisk.trackingshot.animation.SegmentObject
import kotlinx.coroutines.Job
import kotlinx.serialization.Serializable


interface Segment : GenericRunnable {
    val segmentObjects: MutableList<SegmentObject>
    val jobs: MutableList<Job>
    fun isDone(): Boolean

}