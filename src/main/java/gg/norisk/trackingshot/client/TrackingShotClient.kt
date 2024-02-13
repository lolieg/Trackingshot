package gg.norisk.trackingshot.client

import gg.norisk.trackingshot.TrackingShot
import net.fabricmc.api.ClientModInitializer

class TrackingShotClient : ClientModInitializer {
    companion object {
        var trackingShot: TrackingShot? = null
    }
    override fun onInitializeClient() {

    }
}
