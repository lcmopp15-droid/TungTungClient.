# TungTungClient

Pastel Fabric client project for Minecraft Java 1.20.1.

## Current features
- Right Shift opens the pastel ClickGUI.
- Right Control opens the HUD editor.
- ClickGUI toggles HUD, Keystrokes, FPS, CPS, coordinates, animations, and performance preference flags.
- HUD panels can be dragged in the editor and positions/toggles are saved to `config/tungtungclient.json`.
- FPS, CPS, coordinates, WASD, mouse click counters, and Space HUD elements.
- Adds a TungTungClient button to the vanilla title screen.
- GitHub Actions workflow builds the mod and uploads the JAR artifact.

## Build
Requires Java 17 and Gradle 8.x or compatible. Run `gradle build`.

## Install
Use Fabric Loader for Minecraft 1.20.1. Put the built JAR in `.minecraft/mods`.

## Important scope notes
This is an original starter client, not a copy of Lunar Client. The animations and performance toggles are preference flags/placeholders; they do not patch Minecraft rendering behavior by themselves. External Fabric mods must be installed separately and checked for version/license compatibility.
