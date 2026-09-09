package peli1gamer.arsonclient;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ArsonClient implements ClientModInitializer {
    public static final String MOD_ID = "arson-client";
    public static final Logger LOGGER = LoggerFactory.getLogger("Arson Client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Arson Client starting");
        // Module registration is added only after each implementation passes audit.
    }
}
