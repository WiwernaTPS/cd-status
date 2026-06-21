package wiwernatps.cdstatus.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(targets = "net.minecraft.class_1796$class_1797")
public interface CooldownInstanceAccessor {
    @Accessor("comp_3083")
    int getStartTime();

    @Accessor("comp_3084")
    int getEndTime();
}