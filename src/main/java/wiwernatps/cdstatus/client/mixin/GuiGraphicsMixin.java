package wiwernatps.cdstatus.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(class_332.class)
public class GuiGraphicsMixin {
    @ModifyArg(method = "method_64861", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_48196(Lcom/mojang/blaze3d/pipeline/RenderPipeline;IIIII)V"), index = 5)
    private int changeCooldownColor(int color) {
        return 0x40004000;
    }
}