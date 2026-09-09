package peli1gamer.arsonclient.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import peli1gamer.arsonclient.module.Category;
import peli1gamer.arsonclient.module.ConfigurableModule;
import peli1gamer.arsonclient.settings.BoolSetting;
import peli1gamer.arsonclient.settings.DoubleSetting;
import peli1gamer.arsonclient.settings.IntSetting;

/** Attacks a valid entity under the crosshair when the normal attack cooldown is ready. */
public final class TriggerBotModule extends ConfigurableModule {
    private final DoubleSetting range = group("Targeting").add(
        new DoubleSetting("range", "Range", 4.5, 1.0, 8.0, 0.5)
    );
    private final BoolSetting playersOnly = group("Targeting").add(
        new BoolSetting("players-only", "Players Only", true)
    );
    private final BoolSetting criticalsOnly = group("Combat").add(
        new BoolSetting("criticals-only", "Criticals Only", false)
    );
    private final IntSetting cooldown = group("Combat").add(
        new IntSetting("cooldown-percent", "Attack Cooldown %", 100, 1, 100)
    );

    @Override public String id() { return "trigger-bot"; }
    @Override public String name() { return "Trigger Bot"; }
    @Override public Category category() { return Category.PVP; }

    @Override public void onTick() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        MultiPlayerGameMode gameMode = mc.gameMode;
        if (player == null || gameMode == null || mc.hitResult == null) return;
        if (!(mc.hitResult instanceof EntityHitResult hit)) return;
        if (!(hit.getEntity() instanceof LivingEntity target) || target == player || !target.isAlive()) return;
        if (playersOnly.get() && !(target instanceof Player)) return;
        if (player.distanceTo(target) > range.get()) return;
        if (criticalsOnly.get() && (player.onGround() || player.isInWater() || player.isFallFlying())) return;
        if (player.getAttackStrengthScale(0.0F) < cooldown.get() / 100.0F) return;

        gameMode.attack(player, target);
        player.swing(InteractionHand.MAIN_HAND);
    }
}
