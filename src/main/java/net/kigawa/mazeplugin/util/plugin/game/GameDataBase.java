package net.kigawa.mazeplugin.util.plugin.game;

import net.kigawa.mazeplugin.util.plugin.all.recorder.RecorderData;

public abstract class GameDataBase extends RecorderData {
    String world;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
