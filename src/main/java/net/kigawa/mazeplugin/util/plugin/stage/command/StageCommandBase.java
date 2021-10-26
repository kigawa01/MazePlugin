package net.kigawa.mazeplugin.util.plugin.stage.command;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.all.command.Subcommand;
import net.kigawa.mazeplugin.util.plugin.stage.StageManager;

public abstract class StageCommandBase extends Subcommand {
    private final StageManager manager;

    public StageCommandBase(PluginBase kigawaPlugin, StageManager manager) {
        super(kigawaPlugin);
        this.manager = manager;
    }

    public StageManager getManager() {
        return manager;
    }
}

