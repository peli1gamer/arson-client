package peli1gamer.arsonclient.module;
import peli1gamer.arsonclient.settings.SettingGroup;
import java.util.*;
public abstract class ConfigurableModule extends ToggleModule { private final List<SettingGroup> groups=new ArrayList<>(); protected SettingGroup group(String name){SettingGroup g=new SettingGroup(name);groups.add(g);return g;} public List<SettingGroup> settingGroups(){return Collections.unmodifiableList(groups);} }
