package gg.norisk.trackingshot.segments

import gg.norisk.trackingshot.animation.SegmentObject
import gg.norisk.trackingshot.utils
import kotlinx.coroutines.Job

class AnimatedSegment(override val segmentObjects: MutableList<SegmentObject>, override val jobs: MutableList<Job>) : Segment {


    override suspend fun run(): Job? {
            for (animation in segmentObjects) {
                val animationJob = animation.run()
                if (animationJob != null) jobs.add(animationJob)
            }
        return null
    }

    override fun cancel() {
        utils.cancelJobs(jobs)
    }

    override fun isDone(): Boolean {
        return jobs.all { job -> job.isCompleted }
    }
}