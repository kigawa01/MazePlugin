package net.kigawa.mazeplugin.command;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.all.command.Subcommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MazeGenerate extends Subcommand {
    public MazeGenerate(PluginBase kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public String getName() {
        return "generate";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

    @Override
    public String errorMessage() {
        return null;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}
