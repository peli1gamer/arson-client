package peli1gamer.arsonclient.module.impl;
import net.minecraft.client.Minecraft;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.IntSetting;

public final class FullbrightModule extends ConfigurableModule {
 private final IntSetting gamma=group("Visual").add(new IntSetting("gamma-percent","Brightness",100,0,100));
 @Override public String id(){return "fullbright";} @Override public String name(){return "Fullbright";} @Override public Category category(){return Category.RENDER;}
 public int brightness(){return gamma.get();}
 @Override public void onTick(){if(Minecraft.getInstance().player==null)return;}
}
