package gg.norisk.hglobby.mixin.client;

import gg.norisk.hglobby.access.ClientPlayerEntityAccess;
import kotlinx.coroutines.Job;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin implements ClientPlayerEntityAccess {
    @Unique
    private Job trackingShot = null;
    @Override
    public void setTrackingShot(@NotNull Job job) {
        this.trackingShot = job;
    }

    @Override
    public @NotNull Job getTrackingShot() {
        return this.trackingShot;
    }
}
