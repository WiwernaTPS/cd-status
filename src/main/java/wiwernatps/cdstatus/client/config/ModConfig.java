package wiwernatps.cdstatus.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.ColorPicker;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Config(name = "cd-status")
@Environment(EnvType.CLIENT)
public class ModConfig implements ConfigData {
    @CollapsibleObject(startExpanded = true)
    public General general = new General();
    @CollapsibleObject(startExpanded = true)
    public Display display = new Display();
    @CollapsibleObject(startExpanded = true)
    public Pvp pvp = new Pvp();

    public static class General {
        public boolean modEnabled = true;
        public boolean autoHide = true;
        public boolean textShadow = true;
        public boolean background = true;
        @ColorPicker
        public int bgColor = 0x181818;
        @BoundedDiscrete(min = 0, max = 255)
        public int bgAlpha = 180;
        @BoundedDiscrete(min = 0, max = 255)
        public int iconAlpha = 255;
        @ColorPicker
        public int textColor = 0xFFFFFF;
    }

    public static class Display {
        public boolean showNormal = true;
        public float scale = 1.0f;
        public SortMode sortMode = SortMode.VERTICAL;
        public Alignment align = Alignment.LEFT;
        public VerticalAlign verticalAlign = VerticalAlign.TOP;
        @BoundedDiscrete(min = -500, max = 500)
        public int offsetX = 10;
        @BoundedDiscrete(min = -500, max = 500)
        public int offsetY = 10;
    }

    public static class Pvp {
        public boolean enable = true;
        public float scale = 1.0f;
        public SortMode sortMode = SortMode.VERTICAL;
        public Alignment align = Alignment.LEFT;
        public VerticalAlign verticalAlign = VerticalAlign.TOP;
        @BoundedDiscrete(min = -500, max = 500)
        public int offsetX = 10;
        @BoundedDiscrete(min = -500, max = 500)
        public int offsetY = 10;
        public String itemIds = "minecraft:trident,minecraft:ender_pearl,minecraft:shield";
    }

    public enum SortMode { VERTICAL, HORIZONTAL }
    public enum Alignment { LEFT, CENTER, RIGHT }
    public enum VerticalAlign { TOP, MIDDLE, BOTTOM }
}