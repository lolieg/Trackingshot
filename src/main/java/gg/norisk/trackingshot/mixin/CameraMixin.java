package gg.norisk.hglobby.mixin.client;

import gg.norisk.hglobby.client.trackingshot.TrackingShotCamera;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin{


    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        TrackingShotCamera.INSTANCE.update(area, focusedEntity, thirdPerson, inverseView, tickDelta);

    }
}
