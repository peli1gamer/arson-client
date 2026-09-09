package peli1gamer.arsonclient.gui;
import peli1gamer.arsonclient.module.Module;
public record ModuleViewModel(String id,String name,String category,boolean enabled){ public static ModuleViewModel of(Module m){return new ModuleViewModel(m.id(),m.name(),m.category().name(),m.enabled());} }
