package gg.norisk.trackingshot.segments

import gg.norisk.trackingshot.animation.SegmentObject
import gg.norisk.trackingshot.utils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.time.Duration

class DelaySegment(private val delay: Duration,
                   override val segmentObjects: MutableList<SegmentObject>,
                   override val jobs: MutableList<Job>
): Segment {
    override fun isDone(): Boolean {
        return jobs.all { job -> job.isCompleted }
    }

    override suspend fun run(): Job? {
        delay(delay)
        return null
    }

    override fun cancel() {
        utils.cancelJobs(jobs)
    }
}