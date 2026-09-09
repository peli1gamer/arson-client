package peli1gamer.arsonclient.gui;
import peli1gamer.arsonclient.module.*;
import java.util.*;
/** UI state is intentionally separate from Minecraft rendering and module logic. */
public final class ArsonGuiState {
 private GuiPage page=GuiPage.PVP; private String search=""; private String selectedModule;
 public GuiPage page(){return page;} public void page(GuiPage value){page=Objects.requireNonNull(value);selectedModule=null;}
 public String search(){return search;} public void search(String value){search=value==null?"":value;}
 public String selectedModule(){return selectedModule;} public void select(String id){selectedModule=id;}
 public List<Module> visible(ModuleManager manager){String q=search.toLowerCase(Locale.ROOT); return manager.all().stream().filter(m->m.category().name().equals(page.name())).filter(m->q.isBlank()||m.name().toLowerCase(Locale.ROOT).contains(q)||m.id().contains(q)).toList();}
}
