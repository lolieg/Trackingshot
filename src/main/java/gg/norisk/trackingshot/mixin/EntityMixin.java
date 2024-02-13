package gg.norisk.trackingshot.mixin;

import gg.norisk.trackingshot.freecam.FreeCamera;
import gg.norisk.trackingshot.utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    private void onChangeLookDirection(double x, double y, CallbackInfo ci) {
        FreeCamera camera = utils.INSTANCE.getFreeCamera();
        if (camera != null && this.equals(MinecraftClient.getInstance().player)) {
            camera.changeLookDirection(x, y);
            ci.cancel();
        }
    }




}
