package com.tungtung.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayDeque;
import java.util.Deque;

public class TungTungClient implements ClientModInitializer {
    private static KeyBinding openMenu, openEditor;
    static final Deque<Long> LEFT_CLICKS = new ArrayDeque<>(), RIGHT_CLICKS = new ArrayDeque<>();
    private static boolean lastLeft, lastRight;
    @Override public void onInitializeClient() {
        ClientConfig.load();
        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.tungtungclient.open_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.tungtungclient"));
        openEditor = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.tungtungclient.open_hud_editor", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, "category.tungtungclient"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.getWindow() != null) {
                long w=client.getWindow().getHandle(); boolean l=GLFW.glfwGetMouseButton(w, GLFW.GLFW_MOUSE_BUTTON_1)==GLFW.GLFW_PRESS;
                boolean r=GLFW.glfwGetMouseButton(w, GLFW.GLFW_MOUSE_BUTTON_2)==GLFW.GLFW_PRESS; long now=System.currentTimeMillis();
                if(l&&!lastLeft) LEFT_CLICKS.addLast(now); if(r&&!lastRight) RIGHT_CLICKS.addLast(now); lastLeft=l; lastRight=r;
                prune(LEFT_CLICKS, now); prune(RIGHT_CLICKS, now);
            }
            while (openMenu.wasPressed()) if (client.currentScreen == null) client.setScreen(new ClickGuiScreen());
            while (openEditor.wasPressed()) if (client.currentScreen == null) client.setScreen(new HudEditorScreen());
        });
        HudRenderCallback.EVENT.register((draw, tickDelta) -> HudRenderer.render(draw));
    }
    private static void prune(Deque<Long> q,long now) { while(!q.isEmpty() && now-q.peekFirst()>1000) q.removeFirst(); }
    public static int leftCps(){return LEFT_CLICKS.size();} public static int rightCps(){return RIGHT_CLICKS.size();}
}
