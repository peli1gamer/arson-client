package peli1gamer.arsonclient.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;

/** Keeps a totem available in the offhand using normal inventory clicks. */
public final class AutoTotemModule extends ConfigurableModule {
    private final IntSetting health = group("Defense").add(new IntSetting("health-threshold", "Health Threshold", 8, 1, 20));
    private final BoolSetting swapBack = group("Defense").add(new BoolSetting("swap-back", "Swap Back", false));

    @Override public String id(){ return "auto-totem"; }
    @Override public String name(){ return "Auto Totem"; }
    @Override public Category category(){ return Category.PVP; }

    @Override public void onTick() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.gameMode == null) return;
        if (player.getHealth() + player.getAbsorptionAmount() > health.get()) return;
        if (player.getOffhandItem().is(Items.TOTEM_OF_UNDYING)) return;

        int slot = -1;
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getItem(i).is(Items.TOTEM_OF_UNDYING)) { slot = i; break; }
        }
        if (slot < 0) return;

        // Player inventory slot ids are offset inside the standard player container.
        int menuSlot = slot < 9 ? 36 + slot : slot;
        mc.gameMode.handleInventoryMouseClick(player.containerMenu.containerId, menuSlot, 40,
            net.minecraft.world.inventory.ClickType.SWAP, player);
    }

    public int healthThreshold(){ return health.get(); }
    public boolean swapBack(){ return swapBack.get(); }
}