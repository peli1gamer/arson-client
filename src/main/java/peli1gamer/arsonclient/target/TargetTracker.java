package peli1gamer.arsonclient.target;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import peli1gamer.arsonclient.ArsonClient;

/** Detects changes to the local player's last attacker and updates the shared target. */
public final class TargetTracker {
 private int lastHurtTime;
 public void tick(){
  Minecraft mc=Minecraft.getInstance();
  if(mc.player==null) return;
  int hurt=mc.player.hurtTime;
  if(hurt>0 && hurt!=lastHurtTime){
   LivingEntity attacker=mc.player.getLastHurtByMob();
   if(attacker!=null && attacker.isAlive()) ArsonClient.targets().setTarget(attacker);
  }
  lastHurtTime=hurt;
 }
}
