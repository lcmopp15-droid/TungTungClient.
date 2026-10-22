package com.tungtung.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PastelMenuScreen extends Screen {
    private static final int CREAM = 0xFFF8F0E5;
    private static final int PINK = 0xFFE8BFC8;
    private static final int BROWN = 0xFF8B6F65;
    protected PastelMenuScreen() { super(Text.literal("TungTungClient")); }
    @Override public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        int w = 270, h = 190, x = (width-w)/2, y = (height-h)/2;
        ctx.fill(x+4, y+5, x+w+4, y+h+5, 0x55000000);
        ctx.fill(x, y, x+w, y+h, CREAM);
        ctx.fill(x, y, x+w, y+34, PINK);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("TungTungClient"), width/2, y+12, BROWN);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("PASTEL CLIENT • 1.20.1"), width/2, y+48, BROWN);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("HUD editor: coming next"), width/2, y+78, BROWN);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("FPS • CPS • Keystrokes"), width/2, y+98, BROWN);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("Press ESC to close"), width/2, y+h-22, BROWN);
        super.render(ctx, mouseX, mouseY, delta);
    }
    @Override public boolean shouldPause() { return false; }
}
