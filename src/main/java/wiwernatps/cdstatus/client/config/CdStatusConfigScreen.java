package wiwernatps.cdstatus.client.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CdStatusConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("CD Status"))
                .setSavingRunnable(ModConfigManager::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ModConfig config = ModConfigManager.getConfig();

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Mod Enabled"), config.general.modEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.general.modEnabled = v)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Auto Hide"), config.general.autoHide)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.general.autoHide = v)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Text Shadow"), config.general.textShadow)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.general.textShadow = v)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Background"), config.general.background)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.general.background = v)
                .build());
        general.addEntry(entryBuilder.startColorField(Text.literal("Background Color"), config.general.bgColor)
                .setDefaultValue(0x181818)
                .setSaveConsumer(v -> config.general.bgColor = v)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.literal("Background Alpha"), config.general.bgAlpha, 0, 255)
                .setDefaultValue(180)
                .setSaveConsumer(v -> config.general.bgAlpha = v)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.literal("Icon Alpha"), config.general.iconAlpha, 0, 255)
                .setDefaultValue(255)
                .setSaveConsumer(v -> config.general.iconAlpha = v)
                .build());
        general.addEntry(entryBuilder.startColorField(Text.literal("Text Color"), config.general.textColor)
                .setDefaultValue(0xFFFFFF)
                .setSaveConsumer(v -> config.general.textColor = v)
                .build());

        ConfigCategory display = builder.getOrCreateCategory(Text.literal("Normal Items"));
        display.addEntry(entryBuilder.startBooleanToggle(Text.literal("Show"), config.display.showNormal)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.display.showNormal = v)
                .build());
        display.addEntry(entryBuilder.startFloatField(Text.literal("Scale"), config.display.scale)
                .setDefaultValue(1.0f)
                .setSaveConsumer(v -> config.display.scale = v)
                .build());
        display.addEntry(entryBuilder.startEnumSelector(Text.literal("Sort Mode"), ModConfig.SortMode.class, config.display.sortMode)
                .setDefaultValue(ModConfig.SortMode.VERTICAL)
                .setSaveConsumer(v -> config.display.sortMode = v)
                .build());
        display.addEntry(entryBuilder.startEnumSelector(Text.literal("Horizontal Alignment"), ModConfig.Alignment.class, config.display.align)
                .setDefaultValue(ModConfig.Alignment.LEFT)
                .setSaveConsumer(v -> config.display.align = v)
                .build());
        display.addEntry(entryBuilder.startEnumSelector(Text.literal("Vertical Alignment"), ModConfig.VerticalAlign.class, config.display.verticalAlign)
                .setDefaultValue(ModConfig.VerticalAlign.TOP)
                .setSaveConsumer(v -> config.display.verticalAlign = v)
                .build());
        display.addEntry(entryBuilder.startIntSlider(Text.literal("X Offset"), config.display.offsetX, -500, 500)
                .setDefaultValue(10)
                .setSaveConsumer(v -> config.display.offsetX = v)
                .build());
        display.addEntry(entryBuilder.startIntSlider(Text.literal("Y Offset"), config.display.offsetY, -500, 500)
                .setDefaultValue(10)
                .setSaveConsumer(v -> config.display.offsetY = v)
                .build());

        ConfigCategory pvp = builder.getOrCreateCategory(Text.literal("PvP Items"));
        pvp.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Separate Display"), config.pvp.enable)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.pvp.enable = v)
                .build());
        pvp.addEntry(entryBuilder.startFloatField(Text.literal("Scale"), config.pvp.scale)
                .setDefaultValue(1.0f)
                .setSaveConsumer(v -> config.pvp.scale = v)
                .build());
        pvp.addEntry(entryBuilder.startEnumSelector(Text.literal("Sort Mode"), ModConfig.SortMode.class, config.pvp.sortMode)
                .setDefaultValue(ModConfig.SortMode.VERTICAL)
                .setSaveConsumer(v -> config.pvp.sortMode = v)
                .build());
        pvp.addEntry(entryBuilder.startEnumSelector(Text.literal("Horizontal Alignment"), ModConfig.Alignment.class, config.pvp.align)
                .setDefaultValue(ModConfig.Alignment.LEFT)
                .setSaveConsumer(v -> config.pvp.align = v)
                .build());
        pvp.addEntry(entryBuilder.startEnumSelector(Text.literal("Vertical Alignment"), ModConfig.VerticalAlign.class, config.pvp.verticalAlign)
                .setDefaultValue(ModConfig.VerticalAlign.TOP)
                .setSaveConsumer(v -> config.pvp.verticalAlign = v)
                .build());
        pvp.addEntry(entryBuilder.startIntSlider(Text.literal("X Offset"), config.pvp.offsetX, -500, 500)
                .setDefaultValue(10)
                .setSaveConsumer(v -> config.pvp.offsetX = v)
                .build());
        pvp.addEntry(entryBuilder.startIntSlider(Text.literal("Y Offset"), config.pvp.offsetY, -500, 500)
                .setDefaultValue(10)
                .setSaveConsumer(v -> config.pvp.offsetY = v)
                .build());
        pvp.addEntry(entryBuilder.startStrField(Text.literal("Item IDs (comma separated)"), config.pvp.itemIds)
                .setDefaultValue("minecraft:trident,minecraft:ender_pearl,minecraft:shield")
                .setSaveConsumer(v -> config.pvp.itemIds = v)
                .build());

        return builder.build();
    }
}