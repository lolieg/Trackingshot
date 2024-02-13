package gg.norisk.trackingshot

import gg.norisk.hglobby.client.trackingshot.DslAnnotations.TopLevel.TrackingShotDsl

private class DslAnnotations {
    class TopLevel {
        @DslMarker
        annotation class TrackingShotDsl
    }

    class SegmentLevel {
        @DslMarker
        annotation class TrackingShotSegmentDsl

    }

    class AnimationLevel {
        @DslMarker
        annotation class TrackingSHotAnimationDsl
    }
}

@TrackingShotDsl
class TrackingShotBuilder() {

    @TrackingShotDsl
    inner class Segment() {

    }

    @TrackingShotDsl
    inline fun segment() {

    }
}