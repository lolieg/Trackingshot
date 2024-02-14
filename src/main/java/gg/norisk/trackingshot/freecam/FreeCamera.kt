package gg.norisk.trackingshot.freecam

import com.mojang.authlib.GameProfile
import net.minecraft.client.MinecraftClient
import net.minecraft.client.input.Input
import net.minecraft.client.network.ClientConnectionState
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.EntityPose
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.world.entity.EntityChangeListener
import java.util.*

class FreeCamera(id: Int) : ClientPlayerEntity(
    MinecraftClient.getInstance(),
    MinecraftClient.getInstance().world,
    CameraNetworkHandler(
        MinecraftClient.getInstance(),
        MinecraftClient.getInstance().networkHandler?.connection!!,
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
    false) {

    var mode = CameraModes.CAMERA_NORMAL
    var zoom = 1f
    var zNear = -1000f


    init {
        setId(id)
        pose = EntityPose.SWIMMING
        abilities.flying = true
        input = Input()
        abilities.invulnerable = true
        abilities.allowFlying = true
        noClip = true

    }

    fun spawn() {
        if (clientWorld != null) {
            clientWorld.addEntity(this)
            // this caused me hours of pain.
            this.setChangeListener(EntityChangeListener.NONE)
        }
    }

    fun despawn() {
        if (clientWorld != null && clientWorld.getEntityById(id) != null) {
            clientWorld.removeEntity(id, RemovalReason.DISCARDED)
        }
    }

    enum class CameraModes{
        CAMERA_NORMAL,
        CAMERA_ORTHOGRAPHIC
    }

    fun setX(value: Double) {
        this.setPosition(value, y, z)
    }
    fun setY(value: Double) {
        this.setPosition(x, value, z)
    }
    fun setZ(value: Double) {
        this.setPosition(x, y, value)
    }

    override fun setPosition(x: Double, y: Double, z: Double) {
        this.setPos(x, y, z)
    }

    enum class CameraValueType{
        ZOOM,
        MINIMUM_DISTANCE,
        YAW,
        PITCH,

        X_COORDINATE,
        Y_COORDINATE,
        Z_COORDINATE,
    }
}