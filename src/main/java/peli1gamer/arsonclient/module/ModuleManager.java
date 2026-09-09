package peli1gamer.arsonclient.module;

import peli1gamer.arsonclient.ArsonClient;
import java.util.*;

public final class ModuleManager {
    private final Map<String, Module> modules = new LinkedHashMap<>();

    public void register(Module module) {
        Objects.requireNonNull(module, "module");
        if (modules.putIfAbsent(module.id(), module) != null) {
            throw new IllegalStateException("Duplicate module id: " + module.id());
        }
    }

    public Module get(String id) { return modules.get(id); }
    public Collection<Module> all() { return Collections.unmodifiableCollection(modules.values()); }
    public List<Module> byCategory(Category category) {
        return modules.values().stream().filter(m -> m.category() == category).toList();
    }

    /** Run modules independently so one broken module cannot take down the client tick. */
    public void tick() {
        for (Module module : modules.values()) {
            if (!module.enabled()) continue;
            try {
                module.onTick();
            } catch (Throwable t) {
                try { module.setEnabled(false); } catch (Throwable ignored) { }
                ArsonClient.LOGGER.error("Disabling module '{}' after tick failure", module.id(), t);
            }
        }
    }

    public void disableAll() {
        for (Module module : modules.values()) {
            if (!module.enabled()) continue;
            try {
                module.setEnabled(false);
            } catch (Throwable t) {
                ArsonClient.LOGGER.error("Failed to disable module '{}'", module.id(), t);
            }
        }
    }
}
