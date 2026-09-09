package peli1gamer.arsonclient.target;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import java.util.UUID;

/** Shared PvP target state. Consumers never retain stale Entity references. */
public final class TargetManager {
    private UUID targetId;
    private long expiresAt;
    private int timeoutTicks = 200;

    public void setTimeoutTicks(int ticks) { timeoutTicks = Math.max(1, ticks); }
    public void setTarget(Entity entity) {
        if (entity == null || !entity.isAlive()) { clear(); return; }
        targetId = entity.getUUID();
        expiresAt = System.nanoTime() + timeoutTicks * 50_000_000L;
    }
    public Entity target() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || targetId == null || System.nanoTime() > expiresAt) { clear(); return null; }
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (targetId.equals(entity.getUUID()) && entity.isAlive()) return entity;
        }
        clear();
        return null;
    }
    public void tick() { target(); }
    public void clear() { targetId = null; expiresAt = 0L; }
    public boolean hasTarget() { return target() != null; }
}
