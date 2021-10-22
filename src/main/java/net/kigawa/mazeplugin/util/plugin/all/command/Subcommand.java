package net.kigawa.mazeplugin.util.plugin.all.command;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.command.Command;

public abstract class Subcommand extends Command {
    public Subcommand(PluginBase kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 1;
    }
}
