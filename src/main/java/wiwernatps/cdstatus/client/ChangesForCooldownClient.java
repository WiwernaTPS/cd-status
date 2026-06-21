package wiwernatps.cdstatus.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import wiwernatps.cdstatus.client.config.ModConfigManager;
import wiwernatps.cdstatus.client.hud.RenderCooldown;

@Environment(EnvType.CLIENT)
public class ChangesForCooldownClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfigManager.init();
        RenderCooldown.init();
    }
}