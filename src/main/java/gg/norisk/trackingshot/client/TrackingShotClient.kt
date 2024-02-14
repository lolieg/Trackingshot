package gg.norisk.trackingshot.client

import gg.norisk.trackingshot.TrackingShot
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents

class TrackingShotClient : ClientModInitializer {
    companion object {
        var trackingShot: TrackingShot? = null
    }
    override fun onInitializeClient() {
//        ClientPlayerBlockBreakEvents.AFTER.register(ClientPlayerBlockBreakEvents.After { world, player, pos, state ->
//            trackingShot(false) {
//                segment {
//                    orthoCamera()
//                    zoomAnimation(5.seconds, Easing.BACK_OUT, {10f}, {50f})
//                    yAnimation(10.seconds, Easing.EXPO_OUT, {it.y.toFloat()}, {it.y.toFloat() + 100f})
//                    xAnimation(10.seconds, Easing.CIRC_IN, {it.x.toFloat()}, {100f})
//                    yawAnimation(5.seconds, Easing.EXPO_OUT, { it.yaw }, { it.yaw - 360})
//                    pitchAnimation(2.seconds, Easing.EXPO_OUT, {it.pitch}, {80f})
//                }
//            }.start(player)
//        })
        ClientPlayConnectionEvents.DISCONNECT.register(ClientPlayConnectionEvents.Disconnect { handler, client ->
            trackingShot?.exit()
        })
    }
}
