package peli1gamer.arsonclient.settings;
public final class BoolSetting extends Setting<Boolean> { public BoolSetting(String id,String name,boolean value){super(id,name,value);} public void toggle(){set(!get());} }
