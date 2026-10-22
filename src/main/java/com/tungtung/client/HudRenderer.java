package com.tungtung.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public final class HudRenderer {
    static final int CREAM=0xEAF8F0E5, PINK=0xFFE8BFC8, BROWN=0xFF795D54, WHITE=0xFFFFFCF8;
    private HudRenderer(){}
    public static void render(DrawContext ctx) {
        MinecraftClient mc=MinecraftClient.getInstance(); if(mc.player==null || mc.options.hudHidden || !ClientConfig.hudEnabled) return;
        int x=ClientConfig.hudX,y=ClientConfig.hudY; int line=0;
        if(ClientConfig.fps){ pill(ctx,x,y+line*14,"FPS  "+MinecraftClient.getInstance().getCurrentFps()); line++; }
        if(ClientConfig.cps){ pill(ctx,x,y+line*14,"CPS  "+TungTungClient.leftCps()+" | "+TungTungClient.rightCps()); line++; }
        if(ClientConfig.coordinates){ pill(ctx,x,y+line*14,String.format("XYZ  %d / %d / %d",(int)mc.player.getX(),(int)mc.player.getY(),(int)mc.player.getZ())); }
        if(ClientConfig.keystrokes) drawKeys(ctx,ClientConfig.keyX,ClientConfig.keyY,mc);
    }
    static void pill(DrawContext ctx,int x,int y,String s){int w=MinecraftClient.getInstance().textRenderer.getWidth(s)+12;ctx.fill(x,y,x+w,y+12,CREAM);ctx.fill(x,y,x+3,y+12,PINK);ctx.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,Text.literal(s),x+7,y+2,BROWN);}
    static void drawKeys(DrawContext ctx,int x,int y,MinecraftClient mc){
        key(ctx,x+20,y,"W",mc.options.forwardKey.isPressed()); key(ctx,x,y+19,"A",mc.options.leftKey.isPressed()); key(ctx,x+20,y+19,"S",mc.options.backKey.isPressed()); key(ctx,x+40,y+19,"D",mc.options.rightKey.isPressed());
        key(ctx,x,y+38,"LMB "+TungTungClient.leftCps(),mc.mouse.isLeftButtonClicked()); key(ctx,x+48,y+38,"RMB "+TungTungClient.rightCps(),mc.mouse.isRightButtonClicked());
        key(ctx,x,y+57,"SPACE",mc.options.jumpKey.isPressed());
    }
    static void key(DrawContext ctx,int x,int y,String label,boolean pressed){int w=Math.max(18,MinecraftClient.getInstance().textRenderer.getWidth(label)+10);ctx.fill(x,y,x+w,y+16,pressed?PINK:CREAM);ctx.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,Text.literal(label),x+w/2,y+4,BROWN);}
}
