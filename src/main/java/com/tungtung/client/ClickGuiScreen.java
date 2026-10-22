package com.tungtung.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {
    private static final int CREAM=0xFFF8F0E5, PINK=0xFFE8BFC8, BROWN=0xFF795D54;
    public ClickGuiScreen(){super(Text.literal("TungTungClient • ClickGUI"));}
    @Override protected void init(){
        int x=width/2-105,y=height/2-66,w=210,h=20,gap=24;
        addDrawableChild(ButtonWidget.builder(toggle("HUD",ClientConfig.hudEnabled),b->{ClientConfig.hudEnabled=!ClientConfig.hudEnabled;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("Keystrokes",ClientConfig.keystrokes),b->{ClientConfig.keystrokes=!ClientConfig.keystrokes;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("FPS",ClientConfig.fps),b->{ClientConfig.fps=!ClientConfig.fps;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap*2,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("CPS",ClientConfig.cps),b->{ClientConfig.cps=!ClientConfig.cps;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap*3,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("Coordinates",ClientConfig.coordinates),b->{ClientConfig.coordinates=!ClientConfig.coordinates;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap*4,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("Animations",ClientConfig.animations),b->{ClientConfig.animations=!ClientConfig.animations;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap*5,w,h).build());
        addDrawableChild(ButtonWidget.builder(toggle("Performance options",ClientConfig.performance),b->{ClientConfig.performance=!ClientConfig.performance;ClientConfig.save();client.setScreen(new ClickGuiScreen());}).dimensions(x,y+gap*6,w,h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Open HUD editor"),b->client.setScreen(new HudEditorScreen())).dimensions(x,y+gap*7,w,h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"),b->client.setScreen(null)).dimensions(x,y+gap*8,w,h).build());
    }
    private Text toggle(String name,boolean enabled){return Text.literal(name+"  •  "+(enabled?"ON":"OFF"));}
    @Override public void render(DrawContext ctx,int mouseX,int mouseY,float delta){renderBackground(ctx);int x=width/2-125,y=height/2-91;ctx.fill(x+3,y+4,x+253,y+286,0x55000000);ctx.fill(x,y,x+250,y+282,CREAM);ctx.fill(x,y,x+250,y+31,PINK);ctx.drawCenteredTextWithShadow(textRenderer,Text.literal("TungTungClient"),width/2,y+11,BROWN);ctx.drawCenteredTextWithShadow(textRenderer,Text.literal("PASTEL CLICKGUI • 1.20.1"),width/2,y+38,BROWN);super.render(ctx,mouseX,mouseY,delta);}
    @Override public boolean shouldPause(){return false;}
}
