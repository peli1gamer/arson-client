package peli1gamer.arsonclient.gui;
import peli1gamer.arsonclient.module.ConfigurableModule;
import peli1gamer.arsonclient.settings.SettingGroup;
import java.util.*;
/** Data model consumed by the future drawn settings overlay. */
public final class SettingOverlayModel {
 private ConfigurableModule module;
 public void open(ConfigurableModule value){module=Objects.requireNonNull(value);}
 public void close(){module=null;}
 public boolean open(){return module!=null;}
 public ConfigurableModule module(){return module;}
 public List<SettingGroup> groups(){return module==null?List.of():module.settingGroups();}
}
