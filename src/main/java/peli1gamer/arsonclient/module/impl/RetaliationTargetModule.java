package peli1gamer.arsonclient.module.impl;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;
import peli1gamer.arsonclient.ArsonClient;
public final class RetaliationTargetModule extends ConfigurableModule {
 private final IntSetting timeout=group("Target").add(new IntSetting("timeout","Target Timeout (ticks)",200,20,1200));
 @Override public String id(){return "retaliation-target";} @Override public String name(){return "Retaliation Target";} @Override public Category category(){return Category.PVP;}
 @Override public void onTick(){ArsonClient.targets().setTimeoutTicks(timeout.get());}
}
