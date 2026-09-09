package peli1gamer.arsonclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import peli1gamer.arsonclient.ArsonClient;

/** Lightweight HUD data rendering; target is always resolved through TargetManager. */
public final class HudOverlay {
    private HudOverlay() {}
    public static void render(GuiGraphics g) {
        Minecraft mc=Minecraft.getInstance();
        Entity target=ArsonClient.targets().target();
        if(target==null || mc.font==null) return;
        int x=12,y=12;
        g.fill(x-5,y-5,x+170,y+42,ArsonTheme.PANEL);
        g.drawString(mc.font,"TARGET",x,y,ArsonTheme.ACCENT,false);
        g.drawString(mc.font,target.getName().getString(),x,y+13,ArsonTheme.TEXT,false);
        if(mc.player!=null) g.drawString(mc.font,String.format("%.1fm",mc.player.distanceTo(target)),x,y+26,ArsonTheme.MUTED,false);
    }
}
