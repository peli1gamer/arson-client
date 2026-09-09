package peli1gamer.arsonclient.module;

public abstract class ToggleModule implements Module {
    private boolean enabled;

    @Override public final boolean enabled() { return enabled; }

    @Override public final void setEnabled(boolean value) {
        if (enabled == value) return;
        enabled = value;
        try {
            if (value) onEnable();
            else onDisable();
        } catch (Throwable t) {
            enabled = !value;
            throw t;
        }
    }
}
