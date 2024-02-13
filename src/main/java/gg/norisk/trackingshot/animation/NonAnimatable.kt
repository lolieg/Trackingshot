package gg.norisk.trackingshot.animation

import kotlinx.coroutines.Job

class NonAnimatable(val callback: () -> Unit): SegmentObject {


    override suspend fun run(): Job? {
        this@NonAnimatable.callback()
        return null

    }

    override fun cancel() {

    }

}

