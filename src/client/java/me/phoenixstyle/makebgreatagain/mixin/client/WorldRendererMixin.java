package me.phoenixstyle.makebgreatagain.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldRenderer.class)
abstract class WorldRendererMixin {
    @Shadow
    private int blockEntityCount;

@Inject(at = @At("HEAD"), method = "render")
private void clearBlockEntityCountRenderMixin(CallbackInfo callbackInfo) {
    blockEntityCount = 0;
}

    @ModifyExpressionValue(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/render/chunk/ChunkBuilder$ChunkData;getBlockEntities()Ljava/util/List;"),
            method = "render"
    )
    private List<BlockEntity> incrementBlockEntityCountRenderMixin(List<BlockEntity> list) {
        if(!list.isEmpty()) {
            blockEntityCount += list.size();
        }
        return list;
    }
}