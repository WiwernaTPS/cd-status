package wiwernatps.cdstatus.client.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_7923;
import org.joml.Matrix3x2fStack;
import wiwernatps.cdstatus.client.config.ModConfig;
import wiwernatps.cdstatus.client.config.ModConfigManager;
import wiwernatps.cdstatus.client.mixin.CooldownInstanceAccessor;
import wiwernatps.cdstatus.client.mixin.ItemCooldownsAccessor;

import java.util.*;

@Environment(EnvType.CLIENT)
public final class RenderCooldown {
    private static final int ITEM_SIZE = 20;
    private static final int TEXT_GAP = 4;
    private static final int SPACING = 2;

    public static void init() {
        HudElementRegistry.addFirst(
                class_2960.method_60655("cd-status", "before_chat"),
                hudLayer()
        );
    }

    private static HudElement hudLayer() {
        return (guiGraphics, deltaTracker) -> {
            class_310 mc = class_310.method_1551();
            if (mc.field_1724 == null) return;

            ModConfig config = ModConfigManager.getConfig();
            if (!config.general.modEnabled) return;

            class_1796 cooldowns = mc.field_1724.method_7357();
            ItemCooldownsAccessor accessor = (ItemCooldownsAccessor) cooldowns;
            Map<class_2960, Object> cooldownMap = accessor.getCooldowns();
            if (cooldownMap.isEmpty() && config.general.autoHide) return;

            Set<class_2960> pvpSet = parsePvpSet(config.pvp.itemIds);

            List<Map.Entry<class_2960, Object>> normal = new ArrayList<>();
            List<Map.Entry<class_2960, Object>> pvp = new ArrayList<>();
            for (Map.Entry<class_2960, Object> entry : cooldownMap.entrySet()) {
                if (pvpSet.contains(entry.getKey())) {
                    pvp.add(entry);
                } else {
                    normal.add(entry);
                }
            }

            Matrix3x2fStack matrices = guiGraphics.method_51448();

            if (config.display.showNormal && !normal.isEmpty()) {
                renderGroup(guiGraphics, mc, config, normal, config.display, false);
            }
            if (config.pvp.enable && !pvp.isEmpty()) {
                renderGroup(guiGraphics, mc, config, pvp, config.pvp, true);
            }
        };
    }

    private static Set<class_2960> parsePvpSet(String raw) {
        Set<class_2960> set = new HashSet<>();
        if (raw == null || raw.isEmpty()) return set;
        for (String s : raw.split(",")) {
            s = s.trim();
            if (!s.isEmpty()) {
                try {
                    set.add(class_2960.method_60654(s));
                } catch (Exception ignored) {}
            }
        }
        return set;
    }

    private static void renderGroup(class_332 guiGraphics, class_310 mc, ModConfig config,
                                    List<Map.Entry<class_2960, Object>> entries,
                                    ModConfig.Display displaySettings, boolean isPvp) {
        entries.sort(Comparator.comparingDouble(e -> {
            CooldownInstanceAccessor cd = (CooldownInstanceAccessor) e.getValue();
            int tick = ((ItemCooldownsAccessor) mc.field_1724.method_7357()).getTickCount();
            return (cd.getEndTime() - tick) / 20.0;
        }));

        int tickCount = ((ItemCooldownsAccessor) mc.field_1724.method_7357()).getTickCount();
        var textRenderer = mc.field_1772;

        int maxTextWidth = 0;
        for (Map.Entry<class_2960, Object> entry : entries) {
            CooldownInstanceAccessor cd = (CooldownInstanceAccessor) entry.getValue();
            float seconds = (cd.getEndTime() - tickCount) / 20.0f;
            String text = String.format("%.1fs", seconds);
            int w = textRenderer.method_1727(text);
            if (w > maxTextWidth) maxTextWidth = w;
        }

        int itemWidth = ITEM_SIZE;
        int itemHeight = ITEM_SIZE;
        int groupWidth, groupHeight;
        int count = entries.size();
        if (displaySettings.sortMode == ModConfig.SortMode.HORIZONTAL) {
            groupWidth = count * (itemWidth + TEXT_GAP + maxTextWidth) + (count - 1) * SPACING;
            groupHeight = itemHeight + textRenderer.method_1727("A") + 4;
        } else {
            groupWidth = itemWidth + TEXT_GAP + maxTextWidth;
            groupHeight = count * (itemHeight + SPACING) - SPACING;
        }

        float scale = displaySettings.scale;
        int startX = getX(mc, displaySettings.align, displaySettings.offsetX, (int)(groupWidth * scale), scale);
        int startY = getY(mc, displaySettings.verticalAlign, displaySettings.offsetY, (int)(groupHeight * scale), scale);

        Matrix3x2fStack matrices = guiGraphics.method_51448();
        matrices.pushMatrix();
        matrices.translate(0, 0, 0);
        matrices.scale(scale, scale);

        int x = startX;
        int y = startY;
        int bgColor = (config.general.bgAlpha << 24) | (config.general.bgColor & 0xFFFFFF);
        int textColor = config.general.textColor | 0xFF000000;
        boolean shadow = config.general.textShadow;
        boolean background = config.general.background;

        var player = mc.field_1724;
        var inventory = player.method_31548();

        for (Map.Entry<class_2960, Object> entry : entries) {
            class_2960 id = entry.getKey();
            CooldownInstanceAccessor cd = (CooldownInstanceAccessor) entry.getValue();
            float seconds = (cd.getEndTime() - tickCount) / 20.0f;
            String text = String.format("%.1fs", seconds);
            int textWidth = textRenderer.method_1727(text);

            class_1799 stack = null;
            for (class_1799 s : inventory.method_67533()) {
                if (!s.method_7960()) {
                    class_2960 sid = class_1792.method_17960(s.method_7968());
                    if (sid != null && sid.equals(id)) {
                        stack = s;
                        break;
                    }
                }
            }
            if (stack == null || stack.method_7960()) {
                class_1792 item = class_7923.field_41178.method_17966(id).orElse(null);
                if (item == null) continue;
                stack = new class_1799((class_1935) item);
            }

            if (background) {
                int width = itemWidth + TEXT_GAP + textWidth;
                guiGraphics.method_25294(x - 2, y - 2, x + width + 2, y + itemHeight + 2, bgColor);
            }

            guiGraphics.method_51427(stack, x, y);
            int textX = x + itemWidth + TEXT_GAP;
            int textY = y + (itemHeight - textRenderer.method_1727(text)) / 2;
            guiGraphics.method_51433(textRenderer, text, textX, textY, textColor, shadow);

            if (displaySettings.sortMode == ModConfig.SortMode.HORIZONTAL) {
                x += itemWidth + TEXT_GAP + textWidth + SPACING;
            } else {
                y += itemHeight + SPACING;
            }
        }
        matrices.popMatrix();
    }

    private static int getX(class_310 mc, ModConfig.Alignment alignment, int offsetX, int width, float scale) {
        int screenWidth = mc.method_22683().method_4486();
        switch (alignment) {
            case CENTER: return (int)((screenWidth - width) / 2.0f / scale);
            case RIGHT:  return (int)((screenWidth - width - offsetX) / scale);
            default:     return (int)(offsetX / scale);
        }
    }

    private static int getY(class_310 mc, ModConfig.VerticalAlign verticalAlign, int offsetY, int height, float scale) {
        int screenHeight = mc.method_22683().method_4502();
        switch (verticalAlign) {
            case MIDDLE: return (int)((screenHeight - height) / 2.0f / scale);
            case BOTTOM: return (int)((screenHeight - height - offsetY) / scale);
            default:     return (int)(offsetY / scale);
        }
    }
}