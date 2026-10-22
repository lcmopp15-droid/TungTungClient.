package com.tungtung.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ClientConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("tungtungclient.json");
    public static boolean hudEnabled = true, keystrokes = true, fps = true, cps = true, coordinates = true, animations = true, performance = true;
    public static int hudX = 8, hudY = 8, keyX = 8, keyY = 50;
    private ClientConfig() {}
    public static void load() {
        if (!Files.exists(FILE)) { save(); return; }
        try (Reader r = Files.newBufferedReader(FILE)) {
            Data d = GSON.fromJson(r, Data.class);
            if (d == null) return;
            hudEnabled=d.hudEnabled; keystrokes=d.keystrokes; fps=d.fps; cps=d.cps; coordinates=d.coordinates;
            animations=d.animations; performance=d.performance; hudX=d.hudX; hudY=d.hudY; keyX=d.keyX; keyY=d.keyY;
        } catch (Exception ignored) { }
    }
    public static void save() {
        try (Writer w = Files.newBufferedWriter(FILE)) { GSON.toJson(new Data(), w); } catch (Exception ignored) { }
    }
    private static class Data {
        boolean hudEnabled=ClientConfig.hudEnabled, keystrokes=ClientConfig.keystrokes, fps=ClientConfig.fps, cps=ClientConfig.cps, coordinates=ClientConfig.coordinates, animations=ClientConfig.animations, performance=ClientConfig.performance;
        int hudX=ClientConfig.hudX, hudY=ClientConfig.hudY, keyX=ClientConfig.keyX, keyY=ClientConfig.keyY;
    }
}
