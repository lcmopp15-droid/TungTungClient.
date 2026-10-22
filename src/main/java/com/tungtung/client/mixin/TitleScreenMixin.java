package com.tungtung.client.mixin;

import com.tungtung.client.ClickGuiScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Selectable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Shadow protected abstract <T extends Element & Drawable & Selectable> T addDrawableChild(T drawableElement);

    @Inject(method="init", at=@At("TAIL"))
    private void tungtungclient$addButton(CallbackInfo ci) {
        TitleScreen screen=(TitleScreen)(Object)this;
        this.addDrawableChild(ButtonWidget.builder(Text.literal("TungTungClient ✿"), b -> {
            net.minecraft.client.MinecraftClient.getInstance().setScreen(new ClickGuiScreen());
        }).dimensions(screen.width/2-100, screen.height/4+120, 200, 20).build());
    }
}
