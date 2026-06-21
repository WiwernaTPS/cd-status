package wiwernatps.cdstatus.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1796;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(class_1796.class)
public interface ItemCooldownsAccessor {
    @Accessor("field_8024")
    Map<class_2960, Object> getCooldowns();

    @Accessor("field_8025")
    int getTickCount();
}