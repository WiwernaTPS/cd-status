package wiwernatps.cdstatus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangesForCooldown implements ModInitializer {
    public static final String MOD_ID = "cd-status";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("CD Status initialized.");
    }
}