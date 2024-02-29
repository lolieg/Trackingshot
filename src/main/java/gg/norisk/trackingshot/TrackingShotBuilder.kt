package gg.norisk.trackingshot


import gg.norisk.trackingshot.animation.*
import gg.norisk.trackingshot.animation.Animation.Easing
import gg.norisk.trackingshot.client.TrackingShotClient
import gg.norisk.trackingshot.freecam.FreeCamera
import gg.norisk.trackingshot.segments.AnimatedSegment
import gg.norisk.trackingshot.segments.DelaySegment
import gg.norisk.trackingshot.segments.Segment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.math.Vec3d
import kotlin.time.Duration


@DslMarker
annotation class TrackingShotDsl

@DslMarker
annotation class SegmentDsl

    


inline fun trackingShot(
    autoClose: Boolean = true,
    builder: TrackingShotBuilder.() -> Unit
) = TrackingShotBuilder(autoClose).apply(builder).build()


@TrackingShotDsl
class TrackingShotBuilder(val autoClose: Boolean = true) {

    internal val timeline = mutableListOf<Segment>()
    @TrackingShotDsl
    inner class SegmentBuilder {
        val animations = mutableListOf<SegmentObject>()


        @SegmentDsl
        fun normalCamera() {
            animations.add(NonAnimatable { TrackingShotClient.trackingShot?.cameraEntity?.mode = FreeCamera.CameraModes.CAMERA_NORMAL })
        }
        @SegmentDsl
        fun orthoCamera() {
            animations.add(NonAnimatable { TrackingShotClient.trackingShot?.cameraEntity?.mode = FreeCamera.CameraModes.CAMERA_ORTHOGRAPHIC })
        }

        private fun animateCameraProperty(
            duration: Duration,
            easing: Easing,
            startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float,
            endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float,
            cameraValueType: FreeCamera.CameraValueType
        ) {
            animations.add(CameraAnimation(startValue, endValue, duration, easing, cameraValueType))
        }

        @SegmentDsl
        fun playerLookAtCamera(duration: Duration) {
            val player = MinecraftClient.getInstance().player
            if(player != null)
                animations.add(PlayerLookAtCamera(player, duration))
        }
        @SegmentDsl
        fun lookAtPointAnimation(point: Vec3d, duration: Duration) {
            animations.add(LookAtPoint(point, duration))
        }
        @SegmentDsl
        fun yawAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.YAW)
        }
        @SegmentDsl
        fun pitchAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.PITCH)
        }

        @SegmentDsl
        fun zoomAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.ZOOM)
        }
        @SegmentDsl
        fun xAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.X_COORDINATE)
        }
        @SegmentDsl
        fun yAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.Y_COORDINATE)
        }
        @SegmentDsl
        fun zAnimation(duration: Duration, easing: Easing, startValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float, endValue: (player: ClientPlayerEntity, camera: FreeCamera) -> Float) {
            animateCameraProperty(duration, easing, startValue, endValue, FreeCamera.CameraValueType.Z_COORDINATE)
        }



        @PublishedApi
        internal fun build() = AnimatedSegment(this.animations, mutableListOf())
    }

    @TrackingShotDsl
    fun segment(builder: SegmentBuilder.() -> Unit) = timeline.add(SegmentBuilder().apply(builder).build())


    @TrackingShotDsl
    fun wait(delay: Duration) = timeline.add(DelaySegment(delay, mutableListOf(), mutableListOf()))


    @PublishedApi
    internal fun build(): TrackingShot {
        val trackingShot = TrackingShot(timeline, autoClose)
        TrackingShotClient.trackingShot = trackingShot
        return trackingShot
    }
}

typealias CameraValueType = FreeCamera.CameraValueType
typealias Easing = Easing