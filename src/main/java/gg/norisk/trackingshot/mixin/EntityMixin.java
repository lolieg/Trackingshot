package gg.norisk.hglobby.mixin.client;

import gg.norisk.hglobby.client.HglobbyClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixinClient {

    @ModifyVariable(method = "changeLookDirection", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double cursorDeltaX(double value){
        if(HglobbyClient.Companion.getCurrentCameraAnimation() != null && !HglobbyClient.Companion.getCurrentCameraAnimation().isDone()) {
            return 0.0;
        }
        return value;
    }
    @ModifyVariable(method = "changeLookDirection", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private double cursorDeltaY(double value){
        if(HglobbyClient.Companion.getInNavigation()) {
            return 0.0;
        }
        return value;
    }

    @Inject(method = "movementInputToVelocity", at = @At("HEAD"), cancellable = true)
    private static void movement(Vec3d movementInput, float speed, float yaw, CallbackInfoReturnable<Vec3d> cir) {
        if(HglobbyClient.Companion.getInNavigation()) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }

    @Inject(method = "getJumpVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void jump(CallbackInfoReturnable<Float> cir) {
        if(HglobbyClient.Companion.getInNavigation()) {
            cir.setReturnValue(0f);
        }
    }
}
