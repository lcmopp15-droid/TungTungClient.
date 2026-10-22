package com.tungtung.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class HudEditorScreen extends Screen {
    private enum Drag { NONE, INFO, KEYS }
    private Drag drag=Drag.NONE; private int offX,offY;
    public HudEditorScreen(){super(Text.literal("TungTungClient • HUD Editor"));}
    @Override protected void init(){addDrawableChild(ButtonWidget.builder(Text.literal("Save & close"),b->{ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(width/2-55,height-34,110,22).build());}
    @Override public void render(DrawContext ctx,int mx,int my,float delta){
        ctx.fill(0,0,width,height,0xAA241E22);ctx.drawCenteredTextWithShadow(textRenderer,Text.literal("HUD EDITOR"),width/2,12,0xFFFFE8EF);ctx.drawCenteredTextWithShadow(textRenderer,Text.literal("Drag the panels • positions save to config/tungtungclient.json"),width/2,26,0xFFF8F0E5);
        int oldX=ClientConfig.hudX,oldY=ClientConfig.hudY; ClientConfig.hudX=Math.max(2,Math.min(width-80,oldX));ClientConfig.hudY=Math.max(44,Math.min(height-100,oldY));
        int kx=ClientConfig.keyX,ky=ClientConfig.keyY;
        ctx.fill(ClientConfig.hudX-3,ClientConfig.hudY-3,ClientConfig.hudX+170,ClientConfig.hudY+48,0x66E8BFC8);ctx.fill(kx-3,ky-3,kx+100,ky+78,0x66E8BFC8);
        pill(ctx,ClientConfig.hudX,ClientConfig.hudY,"FPS  144");pill(ctx,ClientConfig.hudX,ClientConfig.hudY+14,"CPS  0 | 0");pill(ctx,ClientConfig.hudX,ClientConfig.hudY+28,"XYZ  100 / 64 / -20");
        HudRenderer.key(ctx,kx+20,ky,"W",false);HudRenderer.key(ctx,kx,ky+19,"A",false);HudRenderer.key(ctx,kx+20,ky+19,"S",false);HudRenderer.key(ctx,kx+40,ky+19,"D",false);HudRenderer.key(ctx,kx,ky+38,"LMB",false);HudRenderer.key(ctx,kx+48,ky+38,"RMB",false);HudRenderer.key(ctx,kx,ky+57,"SPACE",false);
        super.render(ctx,mx,my,delta);
    }
    private void pill(DrawContext c,int x,int y,String s){c.fill(x,y,x+130,y+12,HudRenderer.CREAM);c.drawTextWithShadow(textRenderer,Text.literal(s),x+5,y+2,HudRenderer.BROWN);}
    @Override public boolean mouseClicked(double mx,double my,int button){if(button==0){if(mx>=ClientConfig.hudX-4&&mx<=ClientConfig.hudX+174&&my>=ClientConfig.hudY-4&&my<=ClientConfig.hudY+49){drag=Drag.INFO;offX=(int)mx-ClientConfig.hudX;offY=(int)my-ClientConfig.hudY;return true;}if(mx>=ClientConfig.keyX-4&&mx<=ClientConfig.keyX+104&&my>=ClientConfig.keyY-4&&my<=ClientConfig.keyY+80){drag=Drag.KEYS;offX=(int)mx-ClientConfig.keyX;offY=(int)my-ClientConfig.keyY;return true;}}return super.mouseClicked(mx,my,button);}
    @Override public boolean mouseDragged(double mx,double my,int button,double dx,double dy){if(drag==Drag.INFO){ClientConfig.hudX=(int)mx-offX;ClientConfig.hudY=(int)my-offY;return true;}if(drag==Drag.KEYS){ClientConfig.keyX=(int)mx-offX;ClientConfig.keyY=(int)my-offY;return true;}return super.mouseDragged(mx,my,button,dx,dy);}
    @Override public boolean mouseReleased(double mx,double my,int button){if(button==0&&drag!=Drag.NONE){drag=Drag.NONE;ClientConfig.save();return true;}return super.mouseReleased(mx,my,button);}
    @Override public boolean shouldPause(){return false;}
}
