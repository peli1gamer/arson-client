package peli1gamer.arsonclient.module;

import java.util.*;

public final class ModuleManager {
    private final Map<String, Module> modules = new LinkedHashMap<>();

    public void register(Module module) {
        Objects.requireNonNull(module, "module");
        if (modules.putIfAbsent(module.id(), module) != null) throw new IllegalStateException("Duplicate module id: " + module.id());
    }
    public Module get(String id) { return modules.get(id); }
    public Collection<Module> all() { return Collections.unmodifiableCollection(modules.values()); }
    public List<Module> byCategory(Category category) { return modules.values().stream().filter(m -> m.category() == category).toList(); }
    public void tick() { for (Module module : modules.values()) if (module.enabled()) module.onTick(); }
    public void disableAll() { for (Module module : modules.values()) if (module.enabled()) module.setEnabled(false); }
}
