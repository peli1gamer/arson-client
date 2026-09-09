package peli1gamer.arsonclient.module.impl;

import net.minecraft.client.Minecraft;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.BoolSetting;

public final class SprintModule extends ConfigurableModule {
 private final BoolSetting onlyForward=group("General").add(new BoolSetting("only-forward","Only Forward",true));
 @Override public String id(){return "sprint";} @Override public String name(){return "Sprint";} @Override public Category category(){return Category.MOVEMENT;}
 @Override public void onTick(){Minecraft mc=Minecraft.getInstance();if(mc.player==null||mc.options==null)return;if(!onlyForward.get()||mc.options.keyUp.isDown())mc.player.setSprinting(true);}
}
