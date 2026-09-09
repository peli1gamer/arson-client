package peli1gamer.arsonclient.gui;

import peli1gamer.arsonclient.module.ConfigurableModule;
import peli1gamer.arsonclient.module.Module;

public final class ArsonGuiController {
    private final ArsonGuiState state = new ArsonGuiState();
    private final SettingOverlayModel overlay = new SettingOverlayModel();

    public ArsonGuiState state() { return state; }
    public SettingOverlayModel overlay() { return overlay; }
    public void toggle(Module module) { module.setEnabled(!module.enabled()); }
    public void openSettings(Module module) {
        if (module instanceof ConfigurableModule configurable) overlay.open(configurable);
    }
}
