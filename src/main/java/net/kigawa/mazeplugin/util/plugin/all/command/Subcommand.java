package net.kigawa.mazeplugin.util.plugin.all.command;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;

public abstract class Subcommand extends Command {
    public Subcommand(PluginBase kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 1;
    }
}
