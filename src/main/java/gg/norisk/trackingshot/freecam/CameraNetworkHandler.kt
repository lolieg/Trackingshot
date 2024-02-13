package gg.norisk.trackingshot.freecam

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientConnectionState
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.ClientConnection
import net.minecraft.network.packet.Packet

class CameraNetworkHandler(client: MinecraftClient, clientConnection: ClientConnection, clientConnectionState: ClientConnectionState):
    ClientPlayNetworkHandler(client, clientConnection, clientConnectionState) {

    override fun sendPacket(packet: Packet<*>?) {

    }
}