package peli1gamer.arsonclient.module.impl;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;
import java.util.*;
public final class BlockEspModule extends ConfigurableModule {
 public enum BlockGroup { ORES, STORAGE, REDSTONE, SPAWNERS, NETHER, END, CUSTOM }
 private final SetSetting<BlockGroup> blocks=group("Blocks").add(new SetSetting<>("block-groups","Visible Block Groups",List.of(BlockGroup.values()),List.of(BlockGroup.ORES,BlockGroup.STORAGE)));
 private final IntSetting range=group("Rendering").add(new IntSetting("range","Render Range",64,8,256));
 @Override public String id(){return "block-esp";} @Override public String name(){return "Block ESP";} @Override public Category category(){return Category.RENDER;}
 public Set<BlockGroup> blocks(){return blocks.get();}
}
