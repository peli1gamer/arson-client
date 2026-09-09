package peli1gamer.arsonclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peli1gamer.arsonclient.module.ModuleManager;
import peli1gamer.arsonclient.module.impl.AutoTotemModule;
import peli1gamer.arsonclient.module.impl.ElytraFlyModule;\nimport peli1gamer.arsonclient.module.impl.EntityEspModule;\nimport peli1gamer.arsonclient.module.impl.BlockEspModule;\nimport peli1gamer.arsonclient.module.impl.RetaliationTargetModule;
import peli1gamer.arsonclient.target.TargetManager;

public final class ArsonClient implements ClientModInitializer {
    public static final String MOD_ID = "arson-client";
    public static final Logger LOGGER = LoggerFactory.getLogger("Arson Client");
    private static final ModuleManager MODULES = new ModuleManager();
    private static final TargetManager TARGETS = new TargetManager();
    @Override public void onInitializeClient() {
        MODULES.register(new AutoTotemModule());
        MODULES.register(new ElytraFlyModule());\n        MODULES.register(new EntityEspModule());\n        MODULES.register(new BlockEspModule());\n        MODULES.register(new RetaliationTargetModule());
        ClientTickEvents.END_CLIENT_TICK.register(client -> { TARGETS.tick(); MODULES.tick(); });
        LOGGER.info("Arson Client core initialized with {} implemented modules", MODULES.all().size());
    }
    public static ModuleManager modules(){return MODULES;}
    public static TargetManager targets(){return TARGETS;}
}