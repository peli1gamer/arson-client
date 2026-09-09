package peli1gamer.arsonclient.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import peli1gamer.arsonclient.ArsonClient;
import peli1gamer.arsonclient.module.Module;

/** First concrete screen for the rebuilt client UI. */
public final class ArsonClientScreen extends Screen {
    private final ArsonGuiController controller=GuiRegistry.controller();
    private int scroll;
    public ArsonClientScreen(){super(Component.literal("Arson Client"));}
    @Override protected void init(){scroll=0;}
    @Override public void render(GuiGraphics g,int mouseX,int mouseY,float delta){
        g.fill(0,0,width,height,ArsonTheme.BACKGROUND);
        g.fill(16,16,190,height-16,ArsonTheme.PANEL);
        g.drawString(font,"ARSON CLIENT",32,32,ArsonTheme.ACCENT,false);
        int y=64;
        for(GuiPage page:GuiPage.values()){
            boolean selected=controller.state().page()==page;
            if(selected)g.fill(24,y-4,182,y+16,ArsonTheme.ACCENT_DARK);
            g.drawString(font,page.name(),38,y,selected?ArsonTheme.TEXT:ArsonTheme.MUTED,false); y+=24;
        }
        int x=210; g.drawString(font,controller.state().page().name(),x,32,ArsonTheme.TEXT,false);
        int cardY=64-scroll;
        for(Module module:controller.state().visible(ArsonClient.modules())){
            if(cardY>48&&cardY<height){
                g.fill(x,cardY,width-24,cardY+42,ArsonTheme.PANEL);
                g.drawString(font,module.name(),x+12,cardY+8,ArsonTheme.TEXT,false);
                g.drawString(font,module.enabled()?"ON":"OFF",x+12,cardY+24,module.enabled()?ArsonTheme.ACCENT:ArsonTheme.MUTED,false);
            }
            cardY+=50;
        }
        if(controller.overlay().open()){
            int left=width/2-180,top=height/2-140;
            g.fill(left,top,left+360,top+280,ArsonTheme.PANEL);
            g.drawString(font,controller.overlay().module().name()+" Settings",left+18,top+18,ArsonTheme.ACCENT,false);
            int sy=top+46;
            for(var group:controller.overlay().groups()){
                g.drawString(font,group.name(),left+18,sy,ArsonTheme.TEXT,false);sy+=18;
                for(var setting:group.all()){g.drawString(font,setting.name()+": "+setting.get(),left+30,sy,ArsonTheme.MUTED,false);sy+=18;}
                sy+=8;
            }
        }
    }
    @Override public boolean mouseClicked(double mx,double my,int button){
        if(button!=0)return super.mouseClicked(mx,my,button);
        if(controller.overlay().open()){controller.overlay().close();return true;}
        int y=64;
        for(GuiPage page:GuiPage.values()){if(mx>=24&&mx<=182&&my>=y-4&&my<=y+16){controller.state().page(page);return true;}y+=24;}
        int cardY=64-scroll;
        for(Module module:controller.state().visible(ArsonClient.modules())){
            if(mx>=210&&mx<=width-24&&my>=cardY&&my<=cardY+42){if(mx>width-90)controller.openSettings(module);else controller.toggle(module);return true;}cardY+=50;
        }
        return super.mouseClicked(mx,my,button);
    }
    @Override public boolean mouseScrolled(double mx,double my,double dx,double dy){scroll=Math.max(0,scroll-(int)(dy*20));return true;}
}
