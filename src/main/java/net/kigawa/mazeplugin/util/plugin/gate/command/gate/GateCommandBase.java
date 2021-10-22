package net.kigawa.mazeplugin.util.plugin.gate.command.gate;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.command.Subcommand;
import net.kigawa.bordgameplugin.util.plugin.gate.GateManager;

public abstract class GateCommandBase extends Subcommand {
    private final GateManager manager;

    public GateCommandBase(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin);
        this.manager = gate;
    }

    public GateManager getManager() {
        return manager;
    }
}
