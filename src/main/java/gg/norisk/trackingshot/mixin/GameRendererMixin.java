package gg.norisk.hglobby.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.VertexSorter;
import gg.norisk.hglobby.client.trackingshot.CameraManager;
import gg.norisk.hglobby.client.HglobbyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Mutable
    @Shadow @Final private Camera camera;

    @ModifyArg(
            method = "renderWorld",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;setupFrustum(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Vec3d;Lorg/joml/Matrix4f;)V"
            ),
            index = 2
    )
    private Matrix4f orthoFrustumProjMat(Matrix4f projMat) {
        if(HglobbyClient.Companion.getCameraManager().getMode() == CameraManager.CameraModes.ORTHOGRAPHIC) {
            return createOrthoMatrix(1.0F, 20.0F);
        }
        return projMat;
    }

    @ModifyArg(
            method = "renderWorld",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lorg/joml/Matrix4f;)V"

            ),
            index = 7
    )
    private Matrix4f orthoProjMat(Matrix4f projMat, @Local(argsOnly = true) float tickDelta) {

        if(HglobbyClient.Companion.getCameraManager().getMode() == CameraManager.CameraModes.ORTHOGRAPHIC) {
            Matrix4f mat = createOrthoMatrix(tickDelta, 0.0F);
            RenderSystem.setProjectionMatrix(mat, VertexSorter.BY_DISTANCE);
            return mat;
        }
        return projMat;
    }


    @Unique
    private static Matrix4f createOrthoMatrix(float delta, float minScale) {
        MinecraftClient client = MinecraftClient.getInstance();
        float zoom = HglobbyClient.Companion.getCameraManager().getCurrentZoom();
        float zNear = HglobbyClient.Companion.getCameraManager().getMinimumDistance();
        float width = Math.max(minScale, zoom
                * client.getWindow().getFramebufferWidth() / client.getWindow().getFramebufferHeight());
        float height = Math.max(minScale, zoom);
        return new Matrix4f().setOrtho(
                -width, width,
                -height, height,
                zNear, 1000f
        );
    }
}
