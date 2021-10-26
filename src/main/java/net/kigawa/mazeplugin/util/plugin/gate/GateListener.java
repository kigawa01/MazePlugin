package net.kigawa.mazeplugin.util.plugin.gate;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.all.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class GateListener extends Event {
    private final GateManager gate;

    public GateListener(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin);
        this.gate = gate;
    }

    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        gate.moveEvent(event);
    }
}