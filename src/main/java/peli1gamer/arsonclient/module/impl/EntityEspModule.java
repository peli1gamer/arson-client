package peli1gamer.arsonclient.module.impl;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;
import java.util.*;
public final class EntityEspModule extends ConfigurableModule {
 public enum TargetType { PLAYERS, HOSTILE_MOBS, PASSIVE_MOBS, ANIMALS, VILLAGERS, GOLEMS, ITEMS, PROJECTILES }
 private final SetSetting<TargetType> targets=group("Targets").add(new SetSetting<>("targets","Render Targets",List.of(TargetType.values()),List.of(TargetType.PLAYERS,TargetType.HOSTILE_MOBS)));
 private final DoubleSetting range=group("Rendering").add(new DoubleSetting("range","Render Range",64,8,256,1));
 private final BoolSetting throughWalls=group("Rendering").add(new BoolSetting("through-walls","Through Walls",true));
 @Override public String id(){return "entity-esp";} @Override public String name(){return "Entity ESP";} @Override public Category category(){return Category.RENDER;}
 public Set<TargetType> targets(){return targets.get();} public double range(){return range.get();} public boolean throughWalls(){return throughWalls.get();}
}
