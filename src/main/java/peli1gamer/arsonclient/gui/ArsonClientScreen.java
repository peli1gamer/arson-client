package peli1gamer.arsonclient.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import peli1gamer.arsonclient.ArsonClient;
import peli1gamer.arsonclient.module.Module;
import peli1gamer.arsonclient.settings.*;

public final class ArsonClientScreen extends Screen {
    private final ArsonGuiController controller = GuiRegistry.controller();
    private int scroll;

    public ArsonClientScreen() { super(Component.literal("Arson Client")); }

    @Override protected void init() { scroll = 0; }

    @Override public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        g.fill(0, 0, width, height, ArsonTheme.BACKGROUND);
        g.fill(16, 16, 190, height - 16, ArsonTheme.PANEL);
        g.drawString(font, "ARSON CLIENT", 32, 32, ArsonTheme.ACCENT, false);

        int y = 64;
        for (GuiPage page : GuiPage.values()) {
            boolean selected = controller.state().page() == page;
            if (selected) g.fill(24, y - 4, 182, y + 16, ArsonTheme.ACCENT_DARK);
            g.drawString(font, page.name(), 38, y, selected ? ArsonTheme.TEXT : ArsonTheme.MUTED, false);
            y += 24;
        }

        int x = 210;
        g.drawString(font, controller.state().page().name(), x, 32, ArsonTheme.TEXT, false);
        int cardY = 64 - scroll;
        for (Module module : controller.state().visible(ArsonClient.modules())) {
            if (cardY > 48 && cardY < height) {
                g.fill(x, cardY, width - 24, cardY + 42, ArsonTheme.PANEL);
                g.drawString(font, module.name(), x + 12, cardY + 8, ArsonTheme.TEXT, false);
                g.drawString(font, module.enabled() ? "ON" : "OFF", width - 72, cardY + 8,
                    module.enabled() ? ArsonTheme.ACCENT : ArsonTheme.MUTED, false);
                g.drawString(font, "Settings", width - 105, cardY + 25, ArsonTheme.MUTED, false);
            }
            cardY += 50;
        }

        if (controller.overlay().open()) renderOverlay(g);
    }

    private void renderOverlay(GuiGraphics g) {
        int left = width / 2 - 190, top = height / 2 - 150;
        g.fill(left, top, left + 380, top + 300, ArsonTheme.PANEL);
        g.drawString(font, controller.overlay().module().name() + " Settings", left + 18, top + 18, ArsonTheme.ACCENT, false);
        g.drawString(font, "X", left + 355, top + 18, ArsonTheme.MUTED, false);

        int sy = top + 48;
        for (SettingGroup group : controller.overlay().groups()) {
            g.drawString(font, group.name(), left + 18, sy, ArsonTheme.TEXT, false);
            sy += 20;
            for (Setting<?> setting : group.all()) {
                g.fill(left + 14, sy - 3, left + 366, sy + 18, ArsonTheme.BACKGROUND);
                g.drawString(font, setting.name(), left + 24, sy + 2, ArsonTheme.TEXT, false);
                g.drawString(font, valueText(setting), left + 210, sy + 2, ArsonTheme.ACCENT, false);
                sy += 25;
            }
            sy += 6;
        }
    }

    private String valueText(Setting<?> setting) {
        if (setting instanceof BoolSetting b) return b.get() ? "ON" : "OFF";
        if (setting instanceof SetSetting<?> s) return s.get().size() + " selected";
        return String.valueOf(setting.get());
    }

    @Override public boolean mouseClicked(double mx, double my, int button) {
        if (button != 0) return super.mouseClicked(mx, my, button);

        if (controller.overlay().open()) {
            int left = width / 2 - 190, top = height / 2 - 150;
            if (mx >= left + 340 && my >= top && mx <= left + 380 && my <= top + 40) {
                controller.overlay().close();
                return true;
            }

            int sy = top + 68;
            for (SettingGroup group : controller.overlay().groups()) {
                sy += 20;
                for (Setting<?> setting : group.all()) {
                    if (mx >= left + 14 && mx <= left + 366 && my >= sy - 3 && my <= sy + 18) {
                        interact(setting);
                        return true;
                    }
                    sy += 25;
                }
                sy += 6;
            }
            return true;
        }

        int y = 64;
        for (GuiPage page : GuiPage.values()) {
            if (mx >= 24 && mx <= 182 && my >= y - 4 && my <= y + 16) {
                controller.state().page(page);
                return true;
            }
            y += 24;
        }

        int cardY = 64 - scroll;
        for (Module module : controller.state().visible(ArsonClient.modules())) {
            if (mx >= 210 && mx <= width - 24 && my >= cardY && my <= cardY + 42) {
                if (my >= cardY + 20) controller.openSettings(module);
                else controller.toggle(module);
                return true;
            }
            cardY += 50;
        }
        return super.mouseClicked(mx, my, button);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void interact(Setting<?> setting) {
        if (setting instanceof BoolSetting b) b.toggle();
        else if (setting instanceof IntSetting i) i.set(i.get() >= i.max() ? i.min() : i.get() + 1);
        else if (setting instanceof DoubleSetting d) d.set(d.get() + d.step() > d.max() ? d.min() : d.get() + d.step());
        else if (setting instanceof EnumSetting e) {
            Object[] values = e.values();
            int next = (e.get().ordinal() + 1) % values.length;
            e.set((Enum) values[next]);
        }
    }

    @Override public boolean mouseScrolled(double mx, double my, double dx, double dy) {
        scroll = Math.max(0, scroll - (int) (dy * 20));
        return true;
    }
}