package peli1gamer.arsonclient.target;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import peli1gamer.arsonclient.ArsonClient;

/** Tracks the latest living attacker using public entity state. */
public final class TargetTracker {
 private int lastHurtTime;
 public void tick(){Minecraft mc=Minecraft.getInstance();if(mc.player==null){lastHurtTime=0;return;}int hurt=mc.player.hurtTime;if(hurt>0&&hurt!=lastHurtTime){LivingEntity attacker=mc.player.getLastHurtByMob();if(attacker!=null&&attacker.isAlive())ArsonClient.targets().setTarget(attacker);}lastHurtTime=hurt;}
}
