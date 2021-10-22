package net.kigawa.mazeplugin.util.plugin.gate;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.event.Event;
import net.kigawa.bordgameplugin.util.plugin.gate.GateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class GateListener extends Event {
    private final net.kigawa.bordgameplugin.util.plugin.gate.GateManager gate;

    public GateListener(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin);
        this.gate = gate;
    }

    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        gate.moveEvent(event);
    }
}