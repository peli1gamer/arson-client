package peli1gamer.arsonclient.module.impl;

import peli1gamer.arsonclient.ArsonClient;
import peli1gamer.arsonclient.module.Category;
import peli1gamer.arsonclient.module.ConfigurableModule;
import peli1gamer.arsonclient.settings.IntSetting;

/** Keeps the last valid entity that damaged the player as the active target. */
public final class RetaliationTargetModule extends ConfigurableModule {
    private final IntSetting timeout = group("Target").add(
        new IntSetting("timeout", "Target Timeout (ticks)", 200, 20, 1200)
    );

    @Override public String id() { return "retaliation-target"; }
    @Override public String name() { return "Retaliation Target"; }
    @Override public Category category() { return Category.PVP; }

    @Override public void onEnable() {
        ArsonClient.targets().setTimeoutTicks(timeout.get());
    }

    @Override public void onTick() {
        ArsonClient.targets().setTimeoutTicks(timeout.get());
        ArsonClient.targetTracker().tick();
        ArsonClient.targets().tick();
    }

    @Override public void onDisable() {
        ArsonClient.targets().clear();
        ArsonClient.targetTracker().reset();
    }
}
