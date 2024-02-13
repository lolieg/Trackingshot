package gg.norisk.trackingshot

import com.mojang.authlib.GameProfile
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientConnectionState
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.resource.featuretoggle.FeatureSet
import java.util.*

class FreeCamera(val id: Int) :
    ClientPlayerEntity(
        MinecraftClient.getInstance(), MinecraftClient.getInstance().world, ClientPlayNetworkHandler(
            MinecraftClient.getInstance(),
            MinecraftClient.getInstance().networkHandler?.connection,
            ClientConnectionState(
                GameProfile(UUID.randomUUID(), "FreeCamera"),
                MinecraftClient.getInstance().telemetryManager.createWorldSession(false, null, null),
                DynamicRegistryManager.EMPTY,
                FeatureSet.empty(),
                null,
                MinecraftClient.getInstance().currentServerEntry,
                MinecraftClient.getInstance().currentScreen

            )
        ),
        MinecraftClient.getInstance().player?.statHandler,
        MinecraftClient.getInstance().player?.recipeBook,
        false,
        false
    ) {



}