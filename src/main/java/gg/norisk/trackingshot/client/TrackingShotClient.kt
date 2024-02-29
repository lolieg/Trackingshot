package gg.norisk.trackingshot.client

import gg.norisk.trackingshot.TrackingShot
import net.fabricmc.api.ClientModInitializer

class TrackingShotClient : ClientModInitializer {
    companion object {
        var trackingShot: TrackingShot? = null
//        val NAVIGATOR = NavigatorItem()
    }
    override fun onInitializeClient() {
//        ClientPlayerBlockBreakEvents.AFTER.register(ClientPlayerBlockBreakEvents.After { world, player, pos, state ->
//            trackingShot(false) {
//                segment {
//
//                }
//            }
//        })
//        Registry.register(Registries.ITEM, Identifier("trackingshot", "navigator"), NAVIGATOR)
//        ClientPlayConnectionEvents.DISCONNECT.register(ClientPlayConnectionEvents.Disconnect { handler, client ->
//            trackingShot?.exit()
//        })
//        ClientTickEvents.START_CLIENT_TICK.register(ClientTickEvents.StartTick { client ->
//            val player = client.player?: return@StartTick
//
//            // UseItem callbacks don't work reliable with orthocam
//            val item = player.getStackInHand(player.activeHand).item
//            if(item is NavigatorItem && client.options.useKey.wasPressed() && !player.itemCooldownManager.isCoolingDown(item)) {
//                item.toggleNavigation(player)
//                player.itemCooldownManager.set(item, 40)
//            }
//        })
    }
}
