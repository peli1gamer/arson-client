package peli1gamer.arsonclient.settings;
import java.util.*;
public final class SettingGroup { private final String name; private final List<Setting<?>> settings=new ArrayList<>(); public SettingGroup(String name){this.name=name;} public String name(){return name;} public <T extends Setting<?>> T add(T setting){settings.add(setting);return setting;} public List<Setting<?>> all(){return Collections.unmodifiableList(settings);} }
