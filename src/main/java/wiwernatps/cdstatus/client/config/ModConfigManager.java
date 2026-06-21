package wiwernatps.cdstatus.client.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class ModConfigManager {
    private static ModConfig config;

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static ModConfig getConfig() {
        return config;
    }

    public static void save() {
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }
}