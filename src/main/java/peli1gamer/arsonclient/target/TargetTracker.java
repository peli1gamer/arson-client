package peli1gamer.arsonclient.target;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import peli1gamer.arsonclient.ArsonClient;

/** Detects a new living attacker and hands target lifetime to TargetManager. */
public final class TargetTracker {
    private int lastHurtTime;
    private LivingEntity lastAttacker;

    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            reset();
            return;
        }

        int hurt = mc.player.hurtTime;
        LivingEntity attacker = mc.player.getLastHurtByMob();
        boolean newDamage = hurt > 0 && hurt != lastHurtTime;

        if (newDamage && attacker != null && attacker != mc.player && attacker.isAlive()) {
            if (attacker != lastAttacker || !ArsonClient.targets().hasTarget()) {
                ArsonClient.targets().setTarget(attacker);
            } else {
                ArsonClient.targets().refresh();
            }
            lastAttacker = attacker;
        }

        lastHurtTime = hurt;
    }

    public void reset() {
        lastHurtTime = 0;
        lastAttacker = null;
    }
}
