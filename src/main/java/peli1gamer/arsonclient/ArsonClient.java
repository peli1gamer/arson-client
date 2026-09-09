package peli1gamer.arsonclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peli1gamer.arsonclient.module.ModuleManager;
import peli1gamer.arsonclient.target.TargetManager;

public final class ArsonClient implements ClientModInitializer {
    public static final String MOD_ID = "arson-client";
    public static final Logger LOGGER = LoggerFactory.getLogger("Arson Client");
    private static final ModuleManager MODULES = new ModuleManager();
    private static final TargetManager TARGETS = new TargetManager();

    @Override public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> { TARGETS.tick(); MODULES.tick(); });
        LOGGER.info("Arson Client core initialized");
    }
    public static ModuleManager modules(){return MODULES;}
    public static TargetManager targets(){return TARGETS;}
}
