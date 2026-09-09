package peli1gamer.arsonclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peli1gamer.arsonclient.gui.ArsonGui;
import peli1gamer.arsonclient.gui.HudOverlay;
import peli1gamer.arsonclient.module.ModuleManager;
import peli1gamer.arsonclient.module.impl.AutoTotemModule;
import peli1gamer.arsonclient.module.impl.ElytraFlyModule;
import peli1gamer.arsonclient.module.impl.EntityEspModule;
import peli1gamer.arsonclient.module.impl.BlockEspModule;
import peli1gamer.arsonclient.module.impl.RetaliationTargetModule;
import peli1gamer.arsonclient.module.impl.SprintModule;
import peli1gamer.arsonclient.module.impl.FullbrightModule;
import peli1gamer.arsonclient.target.TargetManager;
import peli1gamer.arsonclient.target.TargetTracker;

public final class ArsonClient implements ClientModInitializer {
    public static final String MOD_ID = "arson-client";
    public static final Logger LOGGER = LoggerFactory.getLogger("Arson Client");
    private static final ModuleManager MODULES = new ModuleManager();
    private static final TargetManager TARGETS = new TargetManager();
    private static final TargetTracker TARGET_TRACKER = new TargetTracker();

    @Override
    public void onInitializeClient() {
        MODULES.register(new AutoTotemModule());
        MODULES.register(new ElytraFlyModule());
        MODULES.register(new EntityEspModule());
        MODULES.register(new BlockEspModule());
        MODULES.register(new RetaliationTargetModule());
        MODULES.register(new SprintModule());
        MODULES.register(new FullbrightModule());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            TARGETS.tick();
            MODULES.tick();
        });

        HudRenderCallback.EVENT.register((graphics, tickCounter) -> HudOverlay.render(graphics));

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof TitleScreen) {
                int width = 120;
                int x = 8;
                int y = scaledHeight - 28;
                screen.addRenderableWidget(Button.builder(
                    net.minecraft.network.chat.Component.literal("Arson Client"),
                    button -> ArsonGui.open()
                ).bounds(x, y, width, 20).build());
            }
        });

        LOGGER.info("Arson Client core initialized with {} registered modules", MODULES.all().size());
    }

    public static ModuleManager modules() { return MODULES; }
    public static TargetManager targets() { return TARGETS; }
    public static TargetTracker targetTracker() { return TARGET_TRACKER; }
}
