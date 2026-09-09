package peli1gamer.arsonclient.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;

/** Defensive offhand helper. Uses direct client inventory state only when safe. */
public final class AutoTotemModule extends ConfigurableModule {
 private final IntSetting health=group("Defense").add(new IntSetting("health-threshold","Health Threshold",8,1,20));
 private final BoolSetting swapBack=group("Defense").add(new BoolSetting("swap-back","Swap Back",false));
 @Override public String id(){return "auto-totem";} @Override public String name(){return "Auto Totem";} @Override public Category category(){return Category.PVP;}
 @Override public void onTick(){Minecraft mc=Minecraft.getInstance();Player p=mc.player;if(p==null)return;if(p.getHealth()+p.getAbsorptionAmount()>health.get())return;if(p.getOffhandItem().is(Items.TOTEM_OF_UNDYING))return;}
 public int healthThreshold(){return health.get();} public boolean swapBack(){return swapBack.get();}
}
