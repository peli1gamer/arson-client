package peli1gamer.arsonclient.module.impl;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;
public final class AutoTotemModule extends ConfigurableModule {
 private final IntSetting health=group("Defense").add(new IntSetting("health-threshold","Health Threshold",8,1,20));
 private final BoolSetting swapBack=group("Defense").add(new BoolSetting("swap-back","Swap Back",false));
 @Override public String id(){return "auto-totem";} @Override public String name(){return "Auto Totem";} @Override public Category category(){return Category.PVP;}
 public int healthThreshold(){return health.get();} public boolean swapBack(){return swapBack.get();}
}
